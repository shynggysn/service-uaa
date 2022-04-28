package kz.ne.railways.tezcustoms.service.service.bean;

import com.google.gson.Gson;
import kz.ne.railways.tezcustoms.service.LocalDatabase;
import kz.ne.railways.tezcustoms.service.model.Contract;
import kz.ne.railways.tezcustoms.service.model.asudkr.NeSmgsAdditionDocuments;
import kz.ne.railways.tezcustoms.service.model.asudkr.SearchPIDataModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ForDataBean implements ForDataBeanLocal {

    @Autowired
    private LocalDatabase localDatabase;

    private Gson gson = new Gson();

    @PersistenceContext
    EntityManager em;

    @Override
    public String getContracts(String username) {
        List<Contract> list = localDatabase.contractList;
        List<Contract> result = new ArrayList<Contract>();
        for (Contract contract : list) {
            if (contract.getUser().equals(username))
                result.add(contract);
        }


        return gson.toJson(result);
    }

    @Override
    @Transactional
    public Contract loadContractFromASUDKR(String startSta, String destSta, int expCode, String invoiceNum) {
//        log.debug("{} {} {}", startSta, destSta, invoiceNum);
        StringBuilder sqlWhe = new StringBuilder(" WHERE 1=1 AND pi.CREATE_DATETIME > CURRENT_TIMESTAMP - 180 DAYS ");
        StringBuilder sqlBuilder = new StringBuilder();
        StringBuilder sqlB = new StringBuilder();
        StringBuilder sqlSelsFields = new StringBuilder(
                "select inv.INVC#UN as id, \n"
                        +" inv.INVC_NUM as invoiceNum, \n"
                        +" inv.RECIVE_STATION_CODE as startStation, \n"
                        +" inv.DEST_STATION_CODE as destStation, \n"
                        +" pi.CREATE_DATETIME as createdDate, \n"
                        +" pi.ARRIVE_STA_NO as isPi, \n"
                        +" pi.RESPONSE_DATETIME as responseTime, \n"
                        +" inv.IS_CONTAINER as numVagonContainer, \n "
                        +" '' as containerMark, \n "
                        +" pi.USER#UN as author, "
                        +" pi.PREV_INFO_STATUS as status, \n "
                        +" pi.RESPONSE_TEXT as response,\n"
                        +" pi.PREV_INFO_STATUS as statusPI,\n"
                        +" inv.TRAIN_INDEX as trainIndex, "
                        +" sender.SENDER_NAME as sender, \n "
                        +" receiver.RECIEVER_NAME as receiver, "
                        + "cargo.GNG_CODE as codeGood, "
                        +" '' as nameGood, \n"
                        +" pi.TRANZIT_SEND_DATETIME as tranzitSendDatetime, 'false' as isView, \n"
                        +" inv.DOC_TYPE as docType, inv.IS_CONTAINER as isContainer, inv.INVOICE_DATETIME as invDateTime, \n"
                        +" sts.STA_NAME AS startStaName, std.STA_NAME AS destStaName");

        sqlB.append(" FROM KTZ.NE_INVOICE inv \n");
        sqlB.append(" JOIN KTZ.NE_INVOICE_PREV_INFO pi ON inv.INVC#UN=pi.INVOICE#UN \n");
        sqlB.append(" LEFT JOIN KTZ.NE_SMGS_SENDER_INFO sender ON inv.INVC#UN = sender.INV#UN \n");
        sqlB.append(" LEFT JOIN KTZ.NE_SMGS_RECIEVER_INFO receiver ON inv.INVC#UN = receiver.INV#UN \n");
        sqlB.append(" LEFT JOIN KTZ.NE_SMGS_CARGO cargo ON inv.INVC#UN = cargo.inv#un \n");
        sqlB.append(" LEFT JOIN NSI.STA sts ON sts.STA_NO = inv.RECIVE_STATION_CODE AND sts.ST#END > CURRENT_TIMESTAMP" +
                " LEFT JOIN NSI.STA std ON std.STA_NO = inv.DEST_STATION_CODE AND std.ST#END > CURRENT_TIMESTAMP");

        sqlWhe.append(" and pi.PREV_INFO_STATUS = 1 ");
        sqlWhe.append("AND std.ST#UN = ? ");
        sqlWhe.append("AND sts.ST#UN = ? ");
        sqlWhe.append("AND inv.INVC_NUM = ? ");

        sqlBuilder.append(sqlSelsFields);
        sqlBuilder.append(sqlB);
        sqlBuilder.append(sqlWhe);

        Query searchPIQuery = em.createNativeQuery(sqlBuilder.toString(), SearchPIDataModel.class);

        int i = 0;
        searchPIQuery.setParameter(++i, destSta);
        searchPIQuery.setParameter(++i, startSta);
        searchPIQuery.setParameter(++i, invoiceNum);

        SearchPIDataModel result = (SearchPIDataModel) searchPIQuery.getSingleResult();

        Contract res = new Contract();

        res.setInvoiceId(String.valueOf(result.getId()));
        res.setDepartStation(result.getStartStation() + " " + result.getStartStaName());
        res.setDestStation(result.getDestStation() + " " + result.getDestStaName());
        res.setArrivalStation(result.getIsPi());
        res.setCreationDate(result.getCreatedDate());
        res.setInvoiceNum(result.getInvoiceNum());

        return res;
    }

    @Override
    @Transactional
    public String getUUIDFromASUDKR(String invoiceId) {
        NeSmgsAdditionDocuments document = em.createQuery("select n from NeSmgsAdditionDocuments n where invUn = ?1", NeSmgsAdditionDocuments.class).setParameter(1, Long.parseLong(invoiceId)).getSingleResult();
        return document.getFileUuid();
    }

    @Override
    public void saveContract(Contract contract) {
        localDatabase.contractList.add(contract);
    }

    @Override
    public void saveDocInfo(String invoiceId, String filename, Date date, String uuid) {
        NeSmgsAdditionDocuments document = new NeSmgsAdditionDocuments();
        em.detach(document);
        document.setInvUn(Long.parseLong(invoiceId));
        document.setDocDate(date);
        document.setDocName(filename);
        document.setFileUuid(uuid);

        localDatabase.documents.add(document);
    }
}
