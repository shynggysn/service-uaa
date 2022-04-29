package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.model.Contract;

import java.util.Date;

public interface ForDataBeanLocal {
    public String getContracts();
    public Contract loadContractFromASUDKR(String startSta, String destSta, int expCode, String invoiceNum);
    public String getUUIDFromASUDKR(String invoiceId);
    public void saveContract(Contract contract);
    public void saveDocInfo(String invoiceId, String filename, Date date, String uuid);
}
