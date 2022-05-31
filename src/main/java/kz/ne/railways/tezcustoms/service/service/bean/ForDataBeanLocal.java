package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.model.*;
import kz.ne.railways.tezcustoms.service.model.transit_declaration.SaveDeclarationResponseType;

import java.util.Date;
import java.util.List;

public interface ForDataBeanLocal {

    FormData getContractData(String invNum);

    void saveContractData(Long id, FormData formData, List<VagonItem> vagonList, ContainerDatas containerDatas);

    void saveInvoiceData(InvoiceData invoiceData, Long invoiceUn);

    void saveCustomsResponse(Long invoiceId, SaveDeclarationResponseType result, String uuid);

    void saveDocInfo(String invoiceId, String filename, Date date, String uuid);

    boolean checkExpeditorCode(Long code);
}
