package kz.ne.railways.tezcustoms.service.service.impl;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import kz.ne.railways.tezcustoms.service.model.FormData;
import kz.ne.railways.tezcustoms.service.service.ContractsService;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import kz.ne.railways.tezcustoms.service.util.HttpUtil;
import kz.ne.railways.tezcustoms.service.util.SFtpSend;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ContractsServiceImpl implements ContractsService {

    private final HttpUtil httpUtil;
    private final ForDataBeanLocal dataBean;
    private final SFtpSend fileServer;

    @Override
    public FormData loadContract(String startSta, String destSta, String expCode, String invoiceNum)
                    throws IOException {
        log.debug(" starSta: {}\n destSta: {}\n expCode: {}\n invoiceNum: {}", startSta, destSta, expCode, invoiceNum);
        FormData formData = httpUtil.getContractData(startSta, destSta, expCode, invoiceNum);

        if (formData != null) {
            dataBean.saveContractData(-1L, formData, formData.getVagonList(), formData.getContainerDatas());
            byte[] arr = httpUtil.getContractDoc(formData.getInvoiceId());
            String docname = "Invoice Document";
            String filename = UUID.randomUUID().toString();

            try {
                if (fileServer.send(new ByteArrayInputStream(arr), filename, formData.getInvoiceId()))
                    dataBean.saveDocInfo(formData.getInvoiceId(), docname, new Date(), filename);
            } catch (JSchException | SftpException e) {
                log.debug("in loadContract" + e.getMessage());
            }
        }

        return formData;
    }
}
