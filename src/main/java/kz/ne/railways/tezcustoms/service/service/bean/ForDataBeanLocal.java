package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.model.*;
import kz.ne.railways.tezcustoms.service.model.transit_declaration.SaveDeclarationResponseType;

import java.util.Date;
import java.util.List;

public interface ForDataBeanLocal {

    public FormData getContractData(String invNum);

    public void saveContractData(Long id, FormData formData, List<VagonItem> vagonList, ContainerDatas containerDatas);

    public void saveInvoiceData(InvoiceData invoiceData, Long invoiceUn);

    public void saveCustomsResponse(Long invoiceId, SaveDeclarationResponseType result, String uuid);

    public void saveDocInfo(String invoiceId, String filename, Date date, String uuid);

    public boolean checkExpeditorCode(Long code);
}
