package kz.ne.railways.tezcustoms.service.service.bean;

import kz.ne.railways.tezcustoms.service.model.ContainerDatas;
import kz.ne.railways.tezcustoms.service.model.Contract;
import kz.ne.railways.tezcustoms.service.model.FormData;
import kz.ne.railways.tezcustoms.service.model.VagonItem;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface ForDataBeanLocal {
    public String getContracts();

    public void saveContractData(Long id, FormData formData, List<VagonItem> vagonList, ContainerDatas containerDatas);

    public void saveDocInfo(String invoiceId, String filename, Date date, String uuid);
}
