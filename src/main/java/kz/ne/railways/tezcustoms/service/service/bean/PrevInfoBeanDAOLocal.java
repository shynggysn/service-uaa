package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.entity.asudkr.*;

import java.util.List;

public interface PrevInfoBeanDAOLocal {
    public List<NeVagonLists> getVagonList(Long invoiceUn);

    public NeInvoice getInvoice(Long invoiceUn);

    public NeSmgsCargo getNeSmgsCargo(Long invoiceUn);

    public NeInvoicePrevInfo getInvoicePrevInfo(Long invoiceUn);

    public NeSmgsSenderInfo getSenderInfo(Long invoiceUn);

    public NeSmgsRecieverInfo getRecieverInfo(Long invoiceUn);

    public NeSmgsDestinationPlaceInfo getNeSmgsDestinationPlaceInfo(Long invoiceUn);

    public List<NeSmgsTnVed> getTnVedList(Long invoiceUn);

    public NeVagonGroup getVagonGroup(Long id);

    public NeSmgsTnVedDocuments getSmgsTnVedDocumentsById(Long id);

    public NeSmgsDeclarantInfo getDeclarantInfo(Long invoiceUn);

    public NeSmgsExpeditorInfo getExpeditorInfo(Long invoiceUn);

    public List<NeContainerLists> getContinerList(Long id);

    public NeSmgsShipList getNeSmgsShipList(Long invoiceUn);

}
