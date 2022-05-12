package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.model.ContainerDatas;
import kz.ne.railways.tezcustoms.service.model.Contract;
import kz.ne.railways.tezcustoms.service.model.FormData;
import kz.ne.railways.tezcustoms.service.model.VagonItem;
import kz.ne.railways.tezcustoms.service.model.transitdeclaration.SaveDeclarationResponseType;

import java.util.Date;
import java.util.List;

public interface ForDataBeanLocal {
    public String getContracts();

    public FormData getContractData(String invNum);

    public void saveContractData(Long id, FormData formData, List<VagonItem> vagonList, ContainerDatas containerDatas);

    public void saveCustomsResponse(Long invoiceId, SaveDeclarationResponseType result, String uuid);

    public void saveDocInfo(String invoiceId, String filename, Date date, String uuid);
}
