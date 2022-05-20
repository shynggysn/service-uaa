package kz.ne.railways.tezcustoms.service.service.bean;

import com.google.gson.Gson;
import kz.ne.railways.tezcustoms.service.entity.asudkr.*;
import kz.ne.railways.tezcustoms.service.model.*;
import kz.ne.railways.tezcustoms.service.model.transitdeclaration.SaveDeclarationResponseType;
import kz.ne.railways.tezcustoms.service.util.PIHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class ForDataBean implements ForDataBeanLocal {

    @Autowired
    private PrevInfoBeanDAOLocal dao;

    private Gson gson = new Gson();

    @PersistenceContext
    EntityManager em;

    private static final Long NEW_INVOICE = -1L;

    // Статусы ПИ
    public static int PI_STATUS_IN_WORK = 0; // В работе
    public static int PI_STATUS_SUCCESS_SEND = 1; // Принят в таможне
    public static int PI_STATUS_FAIL_SEND = 2; // Не принят в таможне
    public static int PI_STATUS_TO_CHECK = 3; // Оправлен на проверку
    public static int PI_STATUS_RETURN = 4; // Возврат на оформление
    public static int PI_STATUS_EDIT = 5; // Изменение ТД
    public static int PI_STATUS_EXPORT_TD = 6; // Сформировать ТД

    // Станции где должно указываться транспорт - судно
    List<String> vesselStaUns = Arrays.asList("691607", "693807", "663804", "689202");

    @Override
    public FormData getContractData(String invNum) {
        StringBuilder sqlWhe = new StringBuilder(" WHERE ");
        StringBuilder sqlBuilder = new StringBuilder();
        StringBuilder sqlB = new StringBuilder();
        StringBuilder sqlSelsFields = new StringBuilder("select inv.INVC_UN as id \n");

        sqlB.append(" FROM KTZ.NE_INVOICE inv \n");

        sqlWhe.append("inv.INVC_NUM = ?1 ");

        sqlBuilder.append(sqlSelsFields);
        sqlBuilder.append(sqlB);
        sqlBuilder.append(sqlWhe);

        Query searchPIQuery = em.createNativeQuery(sqlBuilder.toString());
        searchPIQuery.setParameter(1, invNum);
        List<Long> qResult = searchPIQuery.getResultList();
        String invoiceId = String.valueOf(qResult.get(0));

        FormData result = new FormData();
        result.setInvoiceId(invoiceId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        NeInvoice neInvoice = dao.getInvoice(Long.parseLong(invoiceId));
        if (neInvoice != null) { // neInvoice @Table(name="NE_INVOICE")
            result.setTrainIndex(neInvoice.getTrainIndex());
            result.setInvoiceNumber(neInvoice.getInvcNum());
            result.setConteinerRef(Byte.toString(neInvoice.getIsContainer()));
            result.setDocType(neInvoice.getDocType());
            if (neInvoice.getInvoiceDatetime() != null) {
                Date date = new Date(neInvoice.getInvoiceDatetime().getTime());
                result.setInvDateTime(dateFormat.format(date));
            }
            result.setStartStation(neInvoice.getReciveStationCode());
            result.setStartStationName(dao.getStationName(neInvoice.getReciveStationCode(), false));
            result.setDestStation(neInvoice.getDestStationCode());
            result.setDestStationName(dao.getStationName(neInvoice.getDestStationCode(), false));
        }

        GngModel gngModel = getGngModel(Long.parseLong(invoiceId));
        if (gngModel != null) {
            result.setGngCode(gngModel.getCode());
            result.setGngName(gngModel.getShortName1());
        }
        NeInvoicePrevInfo neInvoicePrevInfo = dao.getInvoicePrevInfo(Long.parseLong(invoiceId));
        if (neInvoicePrevInfo != null) {
            Date date = new Date(neInvoicePrevInfo.getCreateDatetime().getTime());
            result.setCreateDate(dateFormat.format(date));
            result.setAppoPrInf(neInvoicePrevInfo.getPrevInfoType());
            result.setFeatureType(neInvoicePrevInfo.getPrevInfoFeatures());
            result.setCustomOrgUn(neInvoicePrevInfo.getCustomOrgUn());
            result.setCustomCode(neInvoicePrevInfo.getCustomCode());
            result.setCustomName(neInvoicePrevInfo.getCustomName());
            result.setArriveStation(neInvoicePrevInfo.getArriveStaNo());
            if (neInvoicePrevInfo.getArriveTime() != null) {
                result.setArrivalDate(neInvoicePrevInfo.getArriveTime());
                result.setArrivalTime(neInvoicePrevInfo.getArriveTime());
            }
            neInvoicePrevInfo.getPrevInfoStatus();
            result.setStartStaCountry(neInvoicePrevInfo.getStartStaCouNo());
            result.setDestStationCountry(neInvoicePrevInfo.getDestStationCouNo());
            result.setResponseMessage(neInvoicePrevInfo.getResponseText());
        }
        NeSmgsSenderInfo senderInfo = dao.getSenderInfo(Long.parseLong(invoiceId));
        if (senderInfo != null) {
            result.setSenderCountry(senderInfo.getSenderCountryCode());
            result.setSenderCountryName(getCountryName(senderInfo.getSenderCountryCode()));
            result.setSenderIndex(senderInfo.getSenderPostIndex());
            result.setSenderShortNam(senderInfo.getSenderName());
            result.setSenderName(senderInfo.getSenderFullName());
            result.setSenderOblast(senderInfo.getSenderRegion());
            result.setSenderPoint(senderInfo.getSenderSity());
            result.setSenderStreetNh(senderInfo.getSenderStreet());
            result.setSenderBIN(senderInfo.getSenderBin());
            result.setSenderIIN(senderInfo.getSenderIin());
            result.setSenderKpp(senderInfo.getKpp());
            result.setSenderKatFace(senderInfo.getCategoryType());
            NePersonCategoryType personCategoryType = getPersonCategoryType(senderInfo.getCategoryType());
            if (personCategoryType != null) {
                result.setSenderKatFaceCode(personCategoryType.getCategoryCode());
                result.setSenderKatFaceName(personCategoryType.getCategoryName());
            }
            NeKatoType neKatoType =
                            getKatoType(senderInfo.getKatoType() != null ? senderInfo.getKatoType().toString() : null);
            if (neKatoType != null) {
                result.setSenderKATO(neKatoType.getKatoCode());
                result.setSenderKATOName(neKatoType.getKatoName());
            }
            result.setSenderITNreserv(senderInfo.getItn());
        }
        NeSmgsRecieverInfo recieverinfo = dao.getRecieverInfo(Long.parseLong(invoiceId));
        if (recieverinfo != null) {
            result.setRecieverCountry(recieverinfo.getRecieverCountryCode());
            result.setRecieverCountryName(getCountryName(recieverinfo.getRecieverCountryCode()));
            result.setRecieverIndex(recieverinfo.getRecieverPostIndex());
            result.setRecieverShortNam(recieverinfo.getRecieverName());
            result.setRecieverName(recieverinfo.getRecieverFullName());
            result.setRecieverOblast(recieverinfo.getRecieverRegion());
            result.setRecieverPoint(recieverinfo.getRecieverSity());
            result.setRecieverStreetNh(recieverinfo.getRecieverStreet());
            result.setRecieverBIN(recieverinfo.getRecieverBin());
            result.setRecieverIIN(recieverinfo.getRecieverIin());
            result.setRecieverKPP(recieverinfo.getKpp());
            result.setRecieverKatFace(recieverinfo.getCategoryType());
            NePersonCategoryType personCategoryType = getPersonCategoryType(recieverinfo.getCategoryType());
            if (personCategoryType != null) {
                result.setRecieverKatFaceCode(personCategoryType.getCategoryCode());
                result.setRecieverKatFaceName(personCategoryType.getCategoryName());
            }
            NeKatoType neKatoType = getKatoType(
                            recieverinfo.getKatoType() != null ? recieverinfo.getKatoType().toString() : null);
            if (neKatoType != null) {
                result.setRecieverKATO(neKatoType.getKatoCode());
                result.setRecieverKATOName(neKatoType.getKatoName());
            }
            result.setRecieverITNreserv(recieverinfo.getItn());
        }
        NeSmgsDestinationPlaceInfo neSmgsDestinationPlaceInfo =
                        dao.getNeSmgsDestinationPlaceInfo(Long.parseLong(invoiceId));
        if (neSmgsDestinationPlaceInfo != null) {
            String destPlaceSta = neSmgsDestinationPlaceInfo.getDestPlaceSta();
            result.setDestPlace(neSmgsDestinationPlaceInfo.getDestPlace());
            result.setDestPlaceStation(destPlaceSta);
            result.setDestPlaceStationName(dao.getStationName(destPlaceSta, true));
            result.setDestPlaceCountryCode(neSmgsDestinationPlaceInfo.getDestPlaceCountryCode());
            result.setDestPlaceIndex(neSmgsDestinationPlaceInfo.getDestPlaceIndex());
            result.setDestPlacePoint(neSmgsDestinationPlaceInfo.getDestPlaceCity());
            result.setDestPlaceOblast(neSmgsDestinationPlaceInfo.getDestPlaceRegion());
            result.setDestPlaceStreet(neSmgsDestinationPlaceInfo.getDestPlaceStreet());
            result.setDestPlaceCustomCode(neSmgsDestinationPlaceInfo.getDestPlaceCustomCode());
            result.setDestPlaceCustomName(neSmgsDestinationPlaceInfo.getDestPlaceCustomName());
            result.setDestPlaceCustomOrgUn(neSmgsDestinationPlaceInfo.getDestPlaceCustomOrgUn());
        }

        NeSmgsDeclarantInfo declarant = dao.getDeclarantInfo(Long.parseLong(invoiceId));
        if (declarant != null) {
            result.setDeclarantAddress(declarant.getDeclarantAddress());
            result.setDeclarantAMNZOU(declarant.getDeclarantAMNZOU());
            result.setDeclarantAMUNN(declarant.getDeclarantAMUNN());
            result.setDeclarantBYIN(declarant.getDeclarantBYIN());
            result.setDeclarantBYUNP(declarant.getDeclarantBYUNP());
            result.setDeclarantCity(declarant.getDeclarantCity());
            result.setDeclarantCountry(declarant.getDeclarantCountry());
            result.setDeclarantCountryName(getCountryNameByCode(declarant.getDeclarantCountry()));
            result.setDeclarantIndex(declarant.getDeclarantIndex());
            result.setDeclarantKGINN(declarant.getDeclarantKGINN());
            result.setDeclarantKGOKPO(declarant.getDeclarantKGOKPO());
            result.setDeclarantKZBin(declarant.getDeclarantKZBin());
            result.setDeclarantKZIin(declarant.getDeclarantKZIin());
            result.setDeclarantKZITN(declarant.getDeclarantKZITN());
            result.setDeclarantKZKATO(declarant.getDeclarantKZKATO());
            result.setDeclarantKZPersonsCategory(declarant.getDeclarantKZPersonsCategory());
            result.setDeclarantName(declarant.getDeclarantName());
            result.setDeclarantRegion(declarant.getDeclarantRegion());
            result.setDeclarantRUINN(declarant.getDeclarantRUINN());
            result.setDeclarantRUKPP(declarant.getDeclarantRUKPP());
            result.setDeclarantRUOGRN(declarant.getDeclarantRUOGRN());
            result.setDeclarantShortName(declarant.getDeclarantShortName());
        }
        NeSmgsExpeditorInfo expeditor = dao.getExpeditorInfo(Long.parseLong(invoiceId));
        if (expeditor != null) {
            result.setExpeditorAddress(expeditor.getExpeditorAddress());
            result.setExpeditorAMNZOU(expeditor.getExpeditorAMNZOU());
            result.setExpeditorAMUNN(expeditor.getExpeditorAMUNN());
            result.setExpeditorBYIN(expeditor.getExpeditorBYIN());
            result.setExpeditorBYUNP(expeditor.getExpeditorBYUNP());
            result.setExpeditorCity(expeditor.getExpeditorCity());
            result.setExpeditorCountry(expeditor.getExpeditorCountry());
            result.setExpeditorCountryName(getCountryNameByCode(expeditor.getExpeditorCountry()));
            result.setExpeditorIndex(expeditor.getExpeditorIndex());
            result.setExpeditorKGINN(expeditor.getExpeditorKGINN());
            result.setExpeditorKGOKPO(expeditor.getExpeditorKGOKPO());
            result.setExpeditorKZBin(expeditor.getExpeditorKZBin());
            result.setExpeditorKZIin(expeditor.getExpeditorKZIin());
            result.setExpeditorKZITN(expeditor.getExpeditorKZITN());
            result.setExpeditorKZKATO(expeditor.getExpeditorKZKATO());
            result.setExpeditorKZPersonsCategory(expeditor.getExpeditorKZPersonsCategory());
            result.setExpeditorName(expeditor.getExpeditorName());
            result.setExpeditorRegion(expeditor.getExpeditorRegion());
            result.setExpeditorRUINN(expeditor.getExpeditorRUINN());
            result.setExpeditorRUKPP(expeditor.getExpeditorRUKPP());
            result.setExpeditorRUOGRN(expeditor.getExpeditorRUOGRN());
            result.setExpeditorShortName(expeditor.getExpeditorShortName());
        }
        if (vesselStaUns.contains(result.getArriveStation())) {
            NeSmgsShipList ship = dao.getNeSmgsShipList(Long.parseLong(invoiceId));
            if (ship != null) {
                result.setVesselUn(ship.getNeVesselUn());
                if (ship.getNeVesselUn() != null) {
                    NeVessel vessel = em.find(NeVessel.class, ship.getNeVesselUn());
                    result.setVessel(new DicDao(vessel.getNeVesselUn(), vessel.getVesselName()));
                }
            }
        }

        if ("1".equals(result.getConteinerRef())) {

            /*
             * NeContainerLists containerLists = contList!=null && !contList.isEmpty() ? contList.get(0):null;
             * if(containerLists != null){ result.setNumContainer(containerLists.getContainerNo());
             * result.setContainerFilled(containerLists.getFilledContainer()); System.out.println("ManagUn " +
             * containerLists.getManagUn()); result.setVagonAccessory(containerLists.getManagUn());
             * result.setContainerMark(containerLists.getContainerMark());
             * result.setContainerCode(containerLists.getConUn()); }
             */
            List<NeContainerLists> contList = dao.getContinerList(Long.parseLong(invoiceId));
            for (NeContainerLists neContainerLists : contList) {
                ContainerData container = new ContainerData();
                container.setContainerListUn(neContainerLists.getContainerListsUn());
                container.setNumContainer(neContainerLists.getContainerNo());
                container.setContainerFilled(neContainerLists.getFilledContainer());
                container.setVagonAccessory(neContainerLists.getManagUn());
                container.setContainerMark(neContainerLists.getContainerMark());
                container.setContainerCode(neContainerLists.getConUn());
                result.addContainer(container);
                Container code = em.find(Container.class, PIHelper.getLongVal(neContainerLists.getConUn()));
                if (code != null) {
                    result.setContainerCode(new DicDao(code.getConUn(), code.getConCode()));
                    container.setContainerCodeName(code.getConCode());
                }

                Management mng = em.find(Management.class, neContainerLists.getManagUn());
                if (mng != null) {
                    Country cntry = em.find(Country.class, mng.getCouUn());
                    if (cntry != null) {
                        result.setContainerCountry(new DicDao(mng.getManagUn(), cntry.getCountryName()));
                        container.setVagonAccessoryName(cntry.getCountryName());
                    }
                }
            }
        } else if ("0".equals(result.getConteinerRef())) {
            result.setVagonAccessory(getManagUnByInvoiceUn(Long.parseLong(invoiceId)));
        }
        return result;
    }

    @Override
    @Transactional
    public void saveInvoiceData(InvoiceData invoiceData, Long invoiceUn) {
        for (InvoiceRow invoiceRow: invoiceData.getInvoiceItems()) {
            NeSmgsTnVed neTnved = new NeSmgsTnVed();
            neTnved.setBruttoWeight(invoiceRow.getBrutto());
            neTnved.setNettoWeight(invoiceRow.getNetto());
            neTnved.setCountByUnit(invoiceRow.getQuantity());
 //           neTnved.setCurrencyCodeUn(invoiceRow.getCurrencyUn());
            neTnved.setInvoiceUn(invoiceUn);
            neTnved.setUnitName(invoiceRow.getUnit());
            neTnved.setPriceByOne(invoiceRow.getPrice());
            neTnved.setPriceByFull(invoiceRow.getTotalPrice());
//            neTnved.setPackingName(invoiceRow.getPackingName());
//            neTnved.setPlaceCargoCount(tnVedRow.getPlaceCargoCount());
//            neTnved.setPlaceCargoMark(tnVedRow.getPlaceCargoMark());
//            String val = tnVedRow.getDescription();
//            if (val != null && val.length() > 200)
//                val = val.substring(0, 200);
//            neTnved.setProductDescription(val);
//            String val2 = tnVedRow.getDescriptionAdditionaly();
//            if (val2 != null && val2.length() > 200)
//                val2 = val2.substring(0, 200);
//            neTnved.setProductDescriptionAdd(val2);
            neTnved.setTnVedCode(invoiceRow.getCode());
//            neTnved.setContainer(tnVedRow.getContainer());
            neTnved.setTnVedName(invoiceRow.getName());
            BigInteger cnt = (BigInteger) em.createNativeQuery(
                            "select count(*) from ktz.ne_smgs_tn_ved a WHERE a.invoice_un = (?1) and a.tn_ved_code = (?2)")
                    .setParameter(1, neTnved.getInvoiceUn()).setParameter(2, neTnved.getTnVedCode()).getSingleResult();
//            neTnved.setTnVedCountry(tnVedRow.getTnVedCountry());
            if (cnt.intValue() == 0)
                em.persist(neTnved);
        }
    }

    @Override
    @Transactional
    public void saveContractData(Long id, FormData formData, List<VagonItem> vagonList, ContainerDatas containerDatas) {
        Long invoiceUn = null;
        NeInvoice invoice = null;
        NeSmgsCargo neSmgsCargo = null;
        NeInvoicePrevInfo neInvoicePrevInfo = null;
        NeSmgsSenderInfo senderInfo = null;
        NeSmgsRecieverInfo recieverinfo = null;
        NeVagonGroup vagonGroup = null;
        NeSmgsDestinationPlaceInfo neSmgsDestinationPlaceInfo = null;
        NeSmgsShipList neSmgsShipList = null;
        NeSmgsDeclarantInfo neSmgsDeclarantInfo = null;
        NeSmgsExpeditorInfo neSmgsExpeditorInfo = null;
        Map<Long, NeSmgsTnVed> neSmgsTnVedMap = new HashMap<Long, NeSmgsTnVed>();
        Map<String, NeVagonLists> neVagonListsMap = new HashMap<String, NeVagonLists>();
        Map<Long, NeContainerLists> containerListsMap = new HashMap<Long, NeContainerLists>();

        if (!NEW_INVOICE.equals(id)) {
            invoice = dao.getInvoice(id);
            neInvoicePrevInfo = dao.getInvoicePrevInfo(id);
            senderInfo = dao.getSenderInfo(id);
            recieverinfo = dao.getRecieverInfo(id);
            neSmgsDestinationPlaceInfo = dao.getNeSmgsDestinationPlaceInfo(id);
            neSmgsShipList = dao.getNeSmgsShipList(id);
            neSmgsDeclarantInfo = dao.getDeclarantInfo(id);
            neSmgsExpeditorInfo = dao.getExpeditorInfo(id);
            neSmgsTnVedMap = getSmgsTnVedMap(id);
            vagonGroup = dao.getVagonGroup(id);
            neVagonListsMap = getNeVagonListsMap(id);
            neSmgsCargo = dao.getNeSmgsCargo(id);
            containerListsMap = getNeContainerListsMap(id);
        }

        invoice = createInvoice(invoice, formData);
        if (invoice.getInvcUn() == null) {
            em.persist(invoice);
        } else {
            em.merge(invoice);
            em.flush();
        }

        invoiceUn = invoice.getInvcUn();
        System.out.println("invc_UN:::::::::::::::::::::::::::::::" + invoiceUn);
        System.out.println("getGngCode:::::::::::::::::::::::::::::::" + formData.getGngCode());
        if (StringUtils.isNotBlank(formData.getGngCode())) {
            neSmgsCargo = createNeSmgsCargo(neSmgsCargo, formData, invoiceUn);
            em.merge(neSmgsCargo);
        }
        neInvoicePrevInfo = createInvoicePrevInfo(neInvoicePrevInfo, formData, invoiceUn);
        em.merge(neInvoicePrevInfo);
        NeSmgsSenderInfo senderInfoToCheckForSolrUpdate = dao.getSenderInfo(invoiceUn);
        NeSmgsRecieverInfo receiverInfoToCheckForSolrUpdate = dao.getRecieverInfo(invoiceUn);
        // NeSmgsDeclarantInfo declarantInfoToCheckForSolrUpdate = dao.getDeclarantInfo(invoiceUn);
        Map<String, String> solrPropertyMap = new HashMap<String, String>();
        solrPropertyMap.put("senderSolrUUID", formData.getSenderSolrUUID());
        solrPropertyMap.put("receiverSolrUUID", formData.getRecieverSolrUUID());
        solrPropertyMap.put("declarantSolrUUID", formData.getDeclarantSolrUUID());
        if (formData.getSenderSolrUUID() == null) {
            if (senderInfoToCheckForSolrUpdate == null) {
                solrPropertyMap.put("senderSolrUUID", UUID.randomUUID().toString());
            } else if (senderInfoToCheckForSolrUpdate.getSenderBin() != null
                            && !senderInfoToCheckForSolrUpdate.getSenderBin().equals(formData.getSenderBIN())) {
                solrPropertyMap.put("senderSolrUUID", UUID.randomUUID().toString());
            } else if (senderInfoToCheckForSolrUpdate.getSenderIin() != null
                            && !senderInfoToCheckForSolrUpdate.getSenderIin().equals(formData.getSenderIIN())) {
                solrPropertyMap.put("senderSolrUUID", UUID.randomUUID().toString());
            }
        }
        if (formData.getRecieverSolrUUID() == null) {
            if (receiverInfoToCheckForSolrUpdate == null) {
                solrPropertyMap.put("receiverSolrUUID", UUID.randomUUID().toString());
            } else if (receiverInfoToCheckForSolrUpdate.getRecieverBin() != null
                            && !receiverInfoToCheckForSolrUpdate.getRecieverBin().equals(formData.getRecieverBIN())) {
                solrPropertyMap.put("receiverSolrUUID", UUID.randomUUID().toString());
            } else if (receiverInfoToCheckForSolrUpdate.getRecieverIin() != null
                            && !receiverInfoToCheckForSolrUpdate.getRecieverIin().equals(formData.getRecieverIIN())) {
                solrPropertyMap.put("receiverSolrUUID", UUID.randomUUID().toString());
            }
        }

        if (declarantInfoFieldsAreNotNull(formData)) {
            neSmgsDeclarantInfo = createDeclarantInfo(neSmgsDeclarantInfo, formData, invoiceUn);
            System.out.println("invc_UN:::::::::::::::::::::::::::::::::" + neSmgsDeclarantInfo.getInvUn());
            em.merge(neSmgsDeclarantInfo);
        }

        if (expeditorInfoFieldsAreNotNull(formData)) {
            neSmgsExpeditorInfo = createExpeditorInfo(neSmgsExpeditorInfo, formData, invoiceUn);
            System.out.println("neSmgsExpeditorInfo invc_UN:::::::::::::::::::::::::::::::::"
                            + neSmgsExpeditorInfo.getInvUn());
            em.merge(neSmgsExpeditorInfo);
        } else if (neSmgsExpeditorInfo != null && neSmgsExpeditorInfo.getInvUn() == invoiceUn) {
            em.remove(neSmgsExpeditorInfo);
        }

        if (senderInfoFieldsAreNotNull(formData)) {
            senderInfo = createSenderInfo(senderInfo, formData, invoiceUn);
            em.merge(senderInfo);
        }
        if (receiverInfoFieldsAreNotNull(formData)) {
            recieverinfo = createRecieverInfo(recieverinfo, formData, invoiceUn);
            em.merge(recieverinfo);
        }

        neSmgsDestinationPlaceInfo = createNeSmgsDestPlaceInfo(neSmgsDestinationPlaceInfo, formData, invoiceUn);
        em.merge(neSmgsDestinationPlaceInfo);

        if (vesselStaUns.contains(formData.getArriveStation())) {
            neSmgsShipList = createNeSmgsShipList(neSmgsShipList, formData, invoiceUn);
            em.merge(neSmgsShipList);
        }


        /* удалить ниже этой строки */
        if (formData.getConteinerRef() != null && "1".equals(formData.getConteinerRef())) {
            // containerLists = createNeContainerLists(invoiceUn,containerLists,containerData);
            // em.merge(containerLists);

            if (containerDatas != null) {
                if (containerDatas.getContainerData() != null && !containerDatas.getContainerData().isEmpty()) {
                    for (ContainerData item : containerDatas.getContainerData()) {
                        NeContainerLists cl = containerListsMap.get(item.getContainerListUn());
                        boolean persist = cl == null;
                        cl = createNeContainerLists(invoiceUn, cl, item);
                        if (persist) {
                            em.persist(cl);
                            System.out.println("++++Insert CL" + cl.getContainerListsUn());
                        } else {
                            em.merge(cl);
                            System.out.println("++++Update CL" + cl.getContainerListsUn());
                        }
                    }
                }

                if (containerDatas.getContainerRemData() != null && !containerDatas.getContainerRemData().isEmpty()) {
                    for (ContainerData item : containerDatas.getContainerRemData()) {
                        NeContainerLists cl = containerListsMap.get(item.getContainerListUn());
                        if (cl != null) {
                            em.remove(cl);
                        }
                    }
                }
            }
        }

        if (vagonList != null && !vagonList.isEmpty()) {
            if (vagonGroup == null) {
                vagonGroup = createNeVagonGroup(vagonGroup);
                em.persist(vagonGroup);
            }
            for (VagonItem vagonItem : vagonList) {
                NeVagonLists neVagonLists = neVagonListsMap.get(vagonItem.getNumber());
                if (formData.getVagonAccessory() != null) {
                    String owner = getManagNoByManagUn(formData.getVagonAccessory());
                    neVagonLists = createNeVagonLists(neVagonLists, vagonItem, invoiceUn, vagonGroup.getVagGroupUn(),
                                    owner);
                } else {
                    neVagonLists = createNeVagonLists(neVagonLists, vagonItem, invoiceUn, vagonGroup.getVagGroupUn(),
                                    null);
                }
                em.merge(neVagonLists);
            }
            em.merge(vagonGroup);
            // Удаление вагонов
            List<String> deletedVagon = getDeletedVagons(neVagonListsMap, vagonList);
            if (deletedVagon != null && !deletedVagon.isEmpty()) {
                // deleteVagonListUnByInvUn(invoiceUn);
                deleteVagonList(deletedVagon, invoiceUn);
            }
        } else if (!neVagonListsMap.isEmpty()) { // Удалить вагоны вместе с группой
            // deleteVagonListUnByInvUn(invoiceUn);
            List<String> deletedVagon = getDeletedVagons(neVagonListsMap, vagonList);
            deleteVagonList(deletedVagon, invoiceUn);
            deleteVagonGroup(vagonGroup, invoiceUn);
        }
        em.flush();
    }

    private Timestamp convertToTimestamp(String timestamp_str) {
        Timestamp result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = new Timestamp(sdf.parse(timestamp_str).getTime());
        } catch (Exception e) {
            log.warn(e.getLocalizedMessage(), e);
        }
        return result;
    }

    private void deleteVagonGroup(NeVagonGroup vagonGroup, Long invoiceUn) {
        if (vagonGroup != null) {
            Integer count = (Integer) em.createNativeQuery(
                            "select count(*) from ktz.NE_VAGON_LISTS WHERE VAG_GROUP_UN = (?1) and INVC_UN <> (?2)")
                            .setParameter(1, vagonGroup.getVagGroupUn()).setParameter(2, invoiceUn).getSingleResult();
            if (count == 0) {
                em.remove(vagonGroup);
            }
        }

    }

    @Transactional
    public void saveCustomsResponse(Long invoiceId, SaveDeclarationResponseType result, String uuid) {
        try {
            NeInvoicePrevInfo invoicePrevInfo = dao.getInvoicePrevInfo(invoiceId);
            if (invoicePrevInfo != null && result != null && result.getValue() != null) {
                String[] message = result.getValue().split("/n");
                if (message.length > 0) {
                    invoicePrevInfo.setResponseText(message[0]);
                }
                if (message.length > 1) {
                    Timestamp timestamp = convertToTimestamp(message[1]);
                    invoicePrevInfo.setResponseDatetime(timestamp);
                }
                if (result.getCode() != null) {
                    Long code = null;
                    try {
                        code = Long.parseLong(result.getCode());
                        invoicePrevInfo.setPrevInfoStatus(
                                        (code.intValue() == 0 ? PI_STATUS_SUCCESS_SEND : PI_STATUS_FAIL_SEND));
                    } catch (Exception e) {
                        log.warn(e.getLocalizedMessage(), e);
                    }
                }
                invoicePrevInfo.setResponseUUID(uuid);
                em.merge(invoicePrevInfo);
                em.flush();
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    private void deleteVagonList(List<String> deletedVagon, Long invoiceUn) {
        if (deletedVagon != null && !deletedVagon.isEmpty() && invoiceUn != null) {
            StringBuffer list_str = new StringBuffer("");
            for (String item : deletedVagon) {
                list_str.append(",");
                list_str.append("'" + item + "'");
            }
            String str1 = list_str.toString();
            str1 = str1.substring(1, str1.length());
            String sql = "DELETE FROM KTZ.NE_VAGON_LISTS where VAG_NO IN (" + str1 + ") AND INVC_UN = "
                            + invoiceUn.toString();
            em.createNativeQuery(sql).executeUpdate();
            em.flush();
        }

    }

    private List<String> getDeletedVagons(Map<String, NeVagonLists> neVagonListsMap, List<VagonItem> vagonList) {
        List<String> result = null;
        if (neVagonListsMap != null && !neVagonListsMap.isEmpty()) {
            Set<String> forDelete = neVagonListsMap.keySet();
            for (VagonItem item : vagonList) {
                forDelete.remove(item.getNumber());
            }
            result = new ArrayList<String>();
            for (String item : forDelete) {
                result.add(item);
            }
        }
        return result;
    }

    private String getManagNoByManagUn(Long managUn) {
        String answer = null;
        String sql = "select m.MANAG_NO from NSI.MANAGEMENT m where m.MANAG_UN=" + managUn.toString();
        try {
            answer = String.valueOf(em.createNativeQuery(sql).getSingleResult());
        } catch (NoResultException e) {
            return null;
        }
        return answer;
    }

    private NeInvoice createInvoice(NeInvoice invoice, FormData formData) {
        if (invoice == null) {
            invoice = new NeInvoice();
        }
        invoice.setTrainIndex(formData.getTrainIndex());
        invoice.setInvcNum(formData.getInvoiceNumber());
        if (formData.getConteinerRef() != null) {
            invoice.setIsContainer(Byte.parseByte(formData.getConteinerRef()));
        }
        invoice.setReciveStationCode(formData.getStartStation());
        invoice.setDestStationCode(formData.getDestStation());
        return invoice;
    }

    private NeInvoicePrevInfo createInvoicePrevInfo(NeInvoicePrevInfo neInvoicePrevInfo, FormData formData,
                    Long invoiceUn) {
        if (neInvoicePrevInfo == null) {
            neInvoicePrevInfo = new NeInvoicePrevInfo();
            neInvoicePrevInfo.setUserUn(formData.getUserUn());
        }
        neInvoicePrevInfo.setPrevInfoType(formData.getAppoPrInf());

        neInvoicePrevInfo.setCustomOrgUn(formData.getCustomOrgUn());

//        // TODO: After transformation to combobox (customCode) sends us _UN
//        if (formData.getCustomCode() != null) {
//            neInvoicePrevInfo.setCustomOrgUn(Long.valueOf(formData.getCustomCode()));
//        }
        // neInvoicePrevInfo.setCustomCode(formData.getCustomCode());
        // neInvoicePrevInfo.setCustomName(formData.getCustomName());
        neInvoicePrevInfo.setPrevInfoFeatures(formData.getFeatureType());
        neInvoicePrevInfo.setInvoiceUn(invoiceUn);
        long dateTime = new Date().getTime();
        if (neInvoicePrevInfo.getCreateDatetime() == null) {
            neInvoicePrevInfo.setCreateDatetime(new Timestamp(dateTime));
        }
        // FIXME: after integration with ws
        // neInvoicePrevInfo.setResponseDatetime(new Timestamp(dateTime));
        // FIXME: response true for now (need to fix response value)
        neInvoicePrevInfo.setArriveStaNo(formData.getArriveStation());
        if (formData.getArrivalTime() != null && formData.getArrivalDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(formData.getArrivalTime().getTime());
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            calendar.setTimeInMillis(formData.getArrivalDate().getTime());
            calendar.set(Calendar.HOUR_OF_DAY, hours);
            calendar.set(Calendar.MINUTE, minutes);
            Timestamp ts = new Timestamp(calendar.getTimeInMillis());
            neInvoicePrevInfo.setArriveTime(ts);
        }
        neInvoicePrevInfo.setStartStaCouNo(formData.getStartStaCountry());
        neInvoicePrevInfo.setDestStationCouNo(formData.getDestStationCountry());
        return neInvoicePrevInfo;
    }

    private Map<Long, NeSmgsTnVed> getSmgsTnVedMap(Long invoiceUn) {
        List<NeSmgsTnVed> list = dao.getTnVedList(invoiceUn);
        Map<Long, NeSmgsTnVed> result = new HashMap<Long, NeSmgsTnVed>();
        if (list != null && list.size() > 0) {
            for (NeSmgsTnVed item : list) {
                result.put(item.getSmgsTnVedUn(), item);
            }
        }
        return result;
    }

    private Map<String, NeVagonLists> getNeVagonListsMap(Long invoiceUn) {
        Map<String, NeVagonLists> result = new HashMap<String, NeVagonLists>();
        List<NeVagonLists> list = dao.getVagonList(invoiceUn);
        if (list != null && !list.isEmpty()) {
            for (NeVagonLists item : list) {
                result.put(item.getVagNo(), item);
            }
        }
        return result;
    }

    private Map<Long, NeContainerLists> getNeContainerListsMap(Long invoiceUn) {
        Map<Long, NeContainerLists> result = new HashMap<Long, NeContainerLists>();
        List<NeContainerLists> list = dao.getContinerList(invoiceUn);
        if (list != null && !list.isEmpty()) {
            for (NeContainerLists item : list) {
                result.put(item.getContainerListsUn(), item);
            }
        }
        return result;
    }

    private boolean receiverInfoFieldsAreNotNull(FormData formData) {
        return StringUtils.isNotBlank(formData.getRecieverName())
                        || StringUtils.isNotBlank(formData.getRecieverShortNam())
                        || StringUtils.isNotBlank(formData.getRecieverCountry())
                        || StringUtils.isNotBlank(formData.getRecieverCountryName())
                        || StringUtils.isNotBlank(formData.getRecieverIndex())
                        || StringUtils.isNotBlank(formData.getRecieverPoint())
                        || StringUtils.isNotBlank(formData.getRecieverOblast())
                        || StringUtils.isNotBlank(formData.getRecieverStreetNh())
                        || StringUtils.isNotBlank(formData.getRecieverBIN())
                        || StringUtils.isNotBlank(formData.getRecieverIIN())
                        || StringUtils.isNotBlank(formData.getRecieverKatFaceCode())
                        || (formData.getRecieverKatFace() != null)
                        // We are not using this properties for now (not need to check)
                        // || StringUtils.isNotBlank(formData.getRecieverKatFaceCode())
                        // || StringUtils.isNotBlank(formData.getRecieverKatFaceName())
                        || StringUtils.isNotBlank(formData.getRecieverKATO())
                        // We are not using this properties for now (not need to check)
                        // || StringUtils.isNotBlank(formData.getRecieverKATOName())
                        || StringUtils.isNotBlank(formData.getRecieverITNreserv())
                        || StringUtils.isNotBlank(formData.getRecieverKPP());
    }

    private boolean declarantInfoFieldsAreNotNull(FormData formData) {
        return StringUtils.isNotBlank(formData.getDeclarantAddress())
                        || StringUtils.isNotBlank(formData.getDeclarantAMNZOU())
                        || StringUtils.isNotBlank(formData.getDeclarantAMUNN())
                        || StringUtils.isNotBlank(formData.getDeclarantBYIN())
                        || StringUtils.isNotBlank(formData.getDeclarantBYUNP())
                        || StringUtils.isNotBlank(formData.getDeclarantCity())
                        || StringUtils.isNotBlank(formData.getDeclarantCountry())
                        || StringUtils.isNotBlank(formData.getDeclarantIndex())
                        || StringUtils.isNotBlank(formData.getDeclarantKGINN())
                        || StringUtils.isNotBlank(formData.getDeclarantKGOKPO())
                        || StringUtils.isNotBlank(formData.getDeclarantKZBin())
                        || StringUtils.isNotBlank(formData.getDeclarantKZIin())
                        || StringUtils.isNotBlank(formData.getDeclarantKZITN())
                        || StringUtils.isNotBlank(formData.getDeclarantKZKATO())
                        || StringUtils.isNotBlank(formData.getDeclarantKZPersonsCategory())
                        || StringUtils.isNotBlank(formData.getDeclarantName())
                        || StringUtils.isNotBlank(formData.getDeclarantRegion())
                        || StringUtils.isNotBlank(formData.getDeclarantRUINN())
                        || StringUtils.isNotBlank(formData.getDeclarantRUKPP())
                        || StringUtils.isNotBlank(formData.getDeclarantRUOGRN())
                        || StringUtils.isNotBlank(formData.getDeclarantShortName());
    }

    private boolean expeditorInfoFieldsAreNotNull(FormData formData) {
        return StringUtils.isNotBlank(formData.getExpeditorAddress())
                        || StringUtils.isNotBlank(formData.getExpeditorAMNZOU())
                        || StringUtils.isNotBlank(formData.getExpeditorAMUNN())
                        || StringUtils.isNotBlank(formData.getExpeditorBYIN())
                        || StringUtils.isNotBlank(formData.getExpeditorBYUNP())
                        || StringUtils.isNotBlank(formData.getExpeditorCity())
                        || StringUtils.isNotBlank(formData.getExpeditorCountry())
                        // || StringUtils.isNotBlank(formData.getExpeditorIndex())
                        || StringUtils.isNotBlank(formData.getExpeditorKGINN())
                        || StringUtils.isNotBlank(formData.getExpeditorKGOKPO())
                        || StringUtils.isNotBlank(formData.getExpeditorKZBin())
                        || StringUtils.isNotBlank(formData.getExpeditorKZIin())
                        || StringUtils.isNotBlank(formData.getExpeditorKZITN())
                        || StringUtils.isNotBlank(formData.getExpeditorKZKATO())
                        || StringUtils.isNotBlank(formData.getExpeditorKZPersonsCategory())
                        || StringUtils.isNotBlank(formData.getExpeditorName())
                        || StringUtils.isNotBlank(formData.getExpeditorRegion())
                        || StringUtils.isNotBlank(formData.getExpeditorRUINN())
                        || StringUtils.isNotBlank(formData.getExpeditorRUKPP())
                        || StringUtils.isNotBlank(formData.getExpeditorRUOGRN())
                        || StringUtils.isNotBlank(formData.getExpeditorShortName());
    }

    private boolean senderInfoFieldsAreNotNull(FormData formData) {
        return StringUtils.isNotBlank(formData.getSenderName()) || StringUtils.isNotBlank(formData.getSenderShortNam())
                        || StringUtils.isNotBlank(formData.getSenderCountry())
                        || StringUtils.isNotBlank(formData.getSenderCountryName())
                        || StringUtils.isNotBlank(formData.getSenderIndex())
                        || StringUtils.isNotBlank(formData.getSenderPoint())
                        || StringUtils.isNotBlank(formData.getSenderOblast())
                        || StringUtils.isNotBlank(formData.getSenderStreetNh())
                        || StringUtils.isNotBlank(formData.getSenderBIN())
                        || StringUtils.isNotBlank(formData.getSenderIIN()) || (formData.getSenderKatFace() != null)
                        // We are not using this properties for now (not need to check)
                        // || StringUtils.isNotBlank(formData.getSenderKatFaceCode())
                        // || StringUtils.isNotBlank(formData.getSenderKatFaceName())
                        || StringUtils.isNotBlank(formData.getSenderKATO())
                        // We are not using this properties for now (not need to check)
                        // || StringUtils.isNotBlank(formData.getRecieverKATOName())
                        || StringUtils.isNotBlank(formData.getSenderITNreserv())
                        || StringUtils.isNotBlank(formData.getSenderKpp());
    }

    private NeContainerLists createNeContainerLists(Long invoiceUn, NeContainerLists containerLists,
                    ContainerData containerData/* , Long vagonListUn */) {
        if (containerLists == null) {
            containerLists = new NeContainerLists();
            containerLists.setInvoiceUn(invoiceUn);
        }
        containerLists.setContainerNo(containerData.getNumContainer());
        containerLists.setFilledContainer(containerData.getContainerFilled());
        if (StringUtils.isNotBlank(containerData.getContainerMark())) {
            containerLists.setContainerMark(containerData.getContainerMark().toUpperCase());
        }
        containerLists.setManagUn(containerData.getVagonAccessory());
        containerLists.setConUn(containerData.getContainerCode());
        // containerLists.setVagonListUn(vagonListUn);
        return containerLists;
    }

    private NeSmgsDeclarantInfo createDeclarantInfo(NeSmgsDeclarantInfo declarantInfo, FormData formData,
                    Long invoiceUn) {
        if (declarantInfo == null) {
            declarantInfo = new NeSmgsDeclarantInfo();
        }

        declarantInfo.setInvUn(invoiceUn);
        declarantInfo.setDeclarantAddress(formData.getDeclarantAddress());
        declarantInfo.setDeclarantAMNZOU(formData.getDeclarantAMNZOU());
        declarantInfo.setDeclarantAMUNN(formData.getDeclarantAMUNN());
        declarantInfo.setDeclarantBYIN(formData.getDeclarantBYIN());
        declarantInfo.setDeclarantBYUNP(formData.getDeclarantBYUNP());
        declarantInfo.setDeclarantCity(formData.getDeclarantCity());
        declarantInfo.setDeclarantCountry(formData.getDeclarantCountry());
        declarantInfo.setDeclarantIndex(formData.getDeclarantIndex());
        declarantInfo.setDeclarantKGINN(formData.getDeclarantKGINN());
        declarantInfo.setDeclarantKGOKPO(formData.getDeclarantKGOKPO());
        declarantInfo.setDeclarantKZBin(formData.getDeclarantKZBin());
        declarantInfo.setDeclarantKZIin(formData.getDeclarantKZIin());
        declarantInfo.setDeclarantKZITN(formData.getDeclarantKZITN());
        declarantInfo.setDeclarantKZKATO(formData.getDeclarantKZKATO());
        declarantInfo.setDeclarantKZPersonsCategory(formData.getDeclarantKZPersonsCategory());
        declarantInfo.setDeclarantName(formData.getDeclarantName());
        declarantInfo.setDeclarantRegion(formData.getDeclarantRegion());
        declarantInfo.setDeclarantRUINN(formData.getDeclarantRUINN());
        declarantInfo.setDeclarantRUKPP(formData.getDeclarantRUKPP());
        declarantInfo.setDeclarantRUOGRN(formData.getDeclarantRUOGRN());
        declarantInfo.setDeclarantShortName(formData.getDeclarantShortName());

        return declarantInfo;
    }

    private NeSmgsExpeditorInfo createExpeditorInfo(NeSmgsExpeditorInfo expeditorInfo, FormData formData,
                    Long invoiceUn) {
        if (expeditorInfo == null) {
            expeditorInfo = new NeSmgsExpeditorInfo();
        }

        expeditorInfo.setInvUn(invoiceUn);
        expeditorInfo.setExpeditorAddress(formData.getExpeditorAddress());
        expeditorInfo.setExpeditorAMNZOU(formData.getExpeditorAMNZOU());
        expeditorInfo.setExpeditorAMUNN(formData.getExpeditorAMUNN());
        expeditorInfo.setExpeditorBYIN(formData.getExpeditorBYIN());
        expeditorInfo.setExpeditorBYUNP(formData.getExpeditorBYUNP());
        expeditorInfo.setExpeditorCity(formData.getExpeditorCity());
        expeditorInfo.setExpeditorCountry(formData.getExpeditorCountry());
        expeditorInfo.setExpeditorIndex(formData.getExpeditorIndex());
        expeditorInfo.setExpeditorKGINN(formData.getExpeditorKGINN());
        expeditorInfo.setExpeditorKGOKPO(formData.getExpeditorKGOKPO());
        expeditorInfo.setExpeditorKZBin(formData.getExpeditorKZBin());
        expeditorInfo.setExpeditorKZIin(formData.getExpeditorKZIin());
        expeditorInfo.setExpeditorKZITN(formData.getExpeditorKZITN());
        expeditorInfo.setExpeditorKZKATO(formData.getExpeditorKZKATO());
        expeditorInfo.setExpeditorKZPersonsCategory(formData.getExpeditorKZPersonsCategory());
        expeditorInfo.setExpeditorName(formData.getExpeditorName());
        expeditorInfo.setExpeditorRegion(formData.getExpeditorRegion());
        expeditorInfo.setExpeditorRUINN(formData.getExpeditorRUINN());
        expeditorInfo.setExpeditorRUKPP(formData.getExpeditorRUKPP());
        expeditorInfo.setExpeditorRUOGRN(formData.getExpeditorRUOGRN());
        expeditorInfo.setExpeditorShortName(formData.getExpeditorShortName());

        return expeditorInfo;
    }

    private NeSmgsSenderInfo createSenderInfo(NeSmgsSenderInfo senderInfo, FormData formData, Long invoiceUn) {
        if (senderInfo == null) {
            senderInfo = new NeSmgsSenderInfo();
        }
        String senderCountryCode = formData.getSenderCountry();
        senderInfo.setInvUn(invoiceUn);
        senderInfo.setSenderCountryCode(senderCountryCode);
        senderInfo.setSenderPostIndex(formData.getSenderIndex());
        senderInfo.setSenderName(formData.getSenderShortNam());
        senderInfo.setSenderFullName(formData.getSenderName());
        senderInfo.setSenderRegion(formData.getSenderOblast());
        senderInfo.setSenderSity(formData.getSenderPoint());
        senderInfo.setSenderStreet(formData.getSenderStreetNh());
        senderInfo.setSenderBin(formData.getSenderBIN());
        senderInfo.setSenderIin(formData.getSenderIIN());
        senderInfo.setKpp(formData.getSenderKpp());
        senderInfo.setCategoryType(formData.getSenderKatFace());
        if (formData.getSenderKATO() != null) {
            senderInfo.setKatoType(Long.parseLong(formData.getSenderKATO()));
        }
        if (formData.getSenderITNreserv() != null) {
            senderInfo.setItn(formData.getSenderITNreserv());
        }
        return senderInfo;
    }

    private NeSmgsRecieverInfo createRecieverInfo(NeSmgsRecieverInfo recieverinfo, FormData formData, Long invoiceUn) {
        if (recieverinfo == null) {
            recieverinfo = new NeSmgsRecieverInfo();
        }
        String recieverCountryCode = formData.getRecieverCountry();
        recieverinfo.setInvUn(invoiceUn);
        recieverinfo.setRecieverCountryCode(recieverCountryCode);
        recieverinfo.setRecieverPostIndex(formData.getRecieverIndex());
        recieverinfo.setRecieverName(formData.getRecieverShortNam());
        recieverinfo.setRecieverFullName(formData.getRecieverName());
        recieverinfo.setRecieverSity(formData.getRecieverPoint());
        recieverinfo.setRecieverRegion(formData.getRecieverOblast());
        recieverinfo.setRecieverStreet(formData.getRecieverStreetNh());
        recieverinfo.setRecieverBin(formData.getRecieverBIN());
        recieverinfo.setRecieverIin(formData.getRecieverIIN());
        recieverinfo.setKpp(formData.getRecieverKPP());
        recieverinfo.setCategoryType(formData.getRecieverKatFace());
        if (formData.getRecieverKATO() != null) {
            recieverinfo.setKatoType(Long.parseLong(formData.getRecieverKATO()));
        }
        if (formData.getRecieverITNreserv() != null) {
            recieverinfo.setItn(formData.getRecieverITNreserv());
        }
        return recieverinfo;
    }

    private NeSmgsDestinationPlaceInfo createNeSmgsDestPlaceInfo(NeSmgsDestinationPlaceInfo neSmgsDestinationPlaceInfo,
                    FormData formData, Long invoiceUn) {
        if (neSmgsDestinationPlaceInfo == null) {
            neSmgsDestinationPlaceInfo = new NeSmgsDestinationPlaceInfo();
        }
        neSmgsDestinationPlaceInfo.setInvoiceUn(invoiceUn);
        neSmgsDestinationPlaceInfo.setDestPlace(formData.getDestPlace());
        neSmgsDestinationPlaceInfo.setDestPlaceSta(formData.getDestPlaceStation());
        neSmgsDestinationPlaceInfo.setDestPlaceCountryCode(formData.getDestPlaceCountryCode());
        neSmgsDestinationPlaceInfo.setDestPlaceIndex(formData.getDestPlaceIndex());
        neSmgsDestinationPlaceInfo.setDestPlaceCity(formData.getDestPlacePoint());
        neSmgsDestinationPlaceInfo.setDestPlaceRegion(formData.getDestPlaceOblast());
        neSmgsDestinationPlaceInfo.setDestPlaceStreet(formData.getDestPlaceStreet());
        neSmgsDestinationPlaceInfo.setDestPlaceCustomCode(formData.getDestPlaceCustomCode());
        neSmgsDestinationPlaceInfo.setDestPlaceCustomName(formData.getDestPlaceCustomName());
        neSmgsDestinationPlaceInfo.setDestPlaceCustomOrgUn(formData.getDestPlaceCustomOrgUn());

        System.out.println("orgUN: " + formData.getDestPlaceCustomOrgUn());

        return neSmgsDestinationPlaceInfo;
    }

    private NeSmgsShipList createNeSmgsShipList(NeSmgsShipList neSmgsShipList, FormData formData, Long invoiceUn) {
        if (neSmgsShipList == null) {
            neSmgsShipList = new NeSmgsShipList();
        }
        neSmgsShipList.setInvUn(invoiceUn);
        neSmgsShipList.setNeVesselUn(formData.getVesselUn());

        return neSmgsShipList;
    }

    private NeVagonGroup createNeVagonGroup(NeVagonGroup vagonGroup) {
        if (vagonGroup == null) {
            /*
             * sender_UN =4397050017; st_UN = 3848500007; vag_group_status_UN=4; date_podach-current_timestamp
             */
            vagonGroup = new NeVagonGroup();
            vagonGroup.setSenderUn(4397050017L);
            vagonGroup.setStUn(3848500007L);
            vagonGroup.setVagGroupStatusUn(4L);
            vagonGroup.setDatePodach(new java.sql.Date(new Date().getTime()));
        }
        return vagonGroup;
    }

    private NeVagonLists createNeVagonLists(NeVagonLists neVagonLists, VagonItem vagonItem, Long invoiceUn,
                    Long vagonGroupUn, String owner) {
        if (neVagonLists == null) {
            neVagonLists = new NeVagonLists();
        }
        neVagonLists.setVagGroupUn(vagonGroupUn);
        neVagonLists.setInvcUn(invoiceUn);
        neVagonLists.setVagNo(vagonItem.getNumber());
        neVagonLists.setOwnerRailways(owner);
        return neVagonLists;
    }

    private NeSmgsCargo createNeSmgsCargo(NeSmgsCargo neSmgsCargo, FormData formData, Long invoiceUn) {
        if (neSmgsCargo == null) {
            neSmgsCargo = new NeSmgsCargo();
        }
        neSmgsCargo.setGngCode(formData.getGngCode());
        neSmgsCargo.setInvUn(invoiceUn);
        // neSmgsCargo.setSenderCountry(senderCountry);
        return neSmgsCargo;
    }

    private String getCountryName(String code) {
        List<Country> countrylist = em
                        .createQuery("select a from Country a where a.countryNo = ?1 and a.couEnd > CURRENT_TIMESTAMP",
                                        Country.class)
                        .setParameter(1, code).getResultList();
        if (countrylist.size() > 0) {
            return countrylist.get(0).getCountryName();
        } else {
            return null;
        }
    }

    private String getCountryNameByCode(String code) {
        if (code == null)
            return null;
        String sql = "select country_name from nsi.country where COU_END > current_timestamp and country_no = ?1";
        Query q = em.createNativeQuery(sql);
        q.setParameter(1, code);
        String country = null;
        try {
            country = (String) q.getSingleResult();
        } catch (NoResultException e) {
        }

        return country;
    }

    private NeKatoType getKatoType(String katoType) {
        if (katoType != null) {
            List<NeKatoType> list = em.createQuery(
                            "select a from NeKatoType a where a.katoCode = ?1 AND a.katoEnd > CURRENT_TIMESTAMP",
                            NeKatoType.class).setParameter(1, katoType).getResultList();
            if (list.size() > 0) {
                return list.get(0);
            }
        }
        return null;
    }

    private Long getManagUnByInvoiceUn(Long invoiceUn) {
        Long answer = null;
        java.math.BigInteger s = null;
        String sql = "select MANAG_UN from nsi.MANAGEMENT where MANAG_NO in (select cast(OWNER_RAILWAYS as SMALLINT) from KTZ.NE_VAGON_LISTS where INVC_UN in(?1)) and MANAG_END>CURRENT_TIMESTAMP";
        Query q = em.createNativeQuery(sql);
        q.setParameter(1, invoiceUn);
        try {
            s = (java.math.BigInteger) q.getSingleResult();
            answer = s.longValue();
        } catch (NoResultException e) {
        }
        return answer;
    }

    private NePersonCategoryType getPersonCategoryType(Long categoryType) {
        List<NePersonCategoryType> list = em.createQuery(
                        "select a from NePersonCategoryType a where a.categoryTypeUn = ?1 and a.categoryEnd > CURRENT_TIMESTAMP",
                        NePersonCategoryType.class).setParameter(1, categoryType).getResultList();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    private GngModel getGngModel(Long invoiceUn) {
        List<GngModel> gngModelList = null;
        Query query = em.createNativeQuery(
                "select a.SMGS_CARGO_UN as id, a.INV_UN as invoiceUn,a.GNG_CODE as code, b.CARGO_SHORTNAME1 as shortName1 from KTZ.NE_SMGS_CARGO a "
                        + "left join NSI.CARGO_GNG b on a.GNG_CODE = b.CARGO_GROUP "
                        + "where a.INV_UN = ?1 and b.C_GN_END > current_timestamp "
                        + "and b.CARGO_SHORTNAME1 is not null "
                        + "fetch first 1 rows only",
                GngModel.class);
        query.setParameter(1, invoiceUn);
        gngModelList = query.getResultList();
        if (gngModelList != null && gngModelList.size() > 0) {
            if (gngModelList.get(0) == null)
                return gngModelList.get(1);
            return gngModelList.get(0);

        }
        return null;
    }

    @Override
    @Transactional
    public void saveDocInfo(String invoiceId, String filename, Date date, String uuid) {
        NeSmgsAdditionDocuments document = new NeSmgsAdditionDocuments();
        document.setInvUn(Long.parseLong(invoiceId));
        document.setDocDate(date);
        document.setDocName(filename);
        document.setFileUuid(uuid);
        em.persist(document);
    }
}
