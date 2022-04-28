package kz.ne.railways.tezcustoms.service.controller;

import com.google.gson.Gson;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.swagger.v3.oas.annotations.Operation;
import kz.ne.railways.tezcustoms.service.model.Contract;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import kz.ne.railways.tezcustoms.service.util.HttpUtil;
import kz.ne.railways.tezcustoms.service.util.SFtpSend;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@RestController
@RequestMapping("/servlet")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ServletController {
    private static final long serialVersionUID = 1L;
    private static final String METHOD = "method";

    private final ForDataBeanLocal dataBean;
    private final HttpUtil httpUtil;
    private final SFtpSend sFtpSend;

    private Gson gson = new Gson();

    @GetMapping
    @Operation(summary = "", description = "")
    public void doGet(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam(name = METHOD, required = true) String method) throws IOException {

    }

    @PostMapping
    @Operation(summary = "", description = "")
    public void doPost(HttpServletRequest request, HttpServletResponse response,
                    @RequestParam(name = METHOD, required = true) String method) throws ServletException, IOException, JSchException, SftpException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String json = null;
        log.debug("doPost");
        if ("getContracts".equals(method)) {
            json = dataBean.getContracts(request.getUserPrincipal().getName()); // TODO: Validate search parameter (username or id)
        } else if ("loadContract".equals(method)) {
            json = loadContract(request);
        } else if ("getContractData".equals(method)) {
            json = getContractData(request);
        }

        if (json != null)
            response.getWriter().write(json);
    }

    private String loadContract(HttpServletRequest request) throws UnsupportedEncodingException, JSchException, SftpException, FileNotFoundException {
        String expCode = request.getParameter("expCode");

        Contract contract = dataBean.loadContractFromASUDKR(request.getParameter("startSta"), request.getParameter("destSta"),
                expCode == null ? -1 : Integer.parseInt(expCode), request.getParameter("invoiceNum"));

        log.debug("Pulled invoiceId: {}", contract.getInvoiceId());
        String uuid = dataBean.getUUIDFromASUDKR(contract.getInvoiceId());
        //Getting scan from the file server

        dataBean.saveContract(contract);

        byte[] arr = {};
        OutputStream out = (OutputStream) Stream.of(arr);
        try {
            sFtpSend.get(contract.getInvoiceId(), uuid, out);
            out.flush();
        } catch (Exception e) {
            log.debug("Error when getting a contract from ASUDKRR: ", e);
            return "";
        }

        String docname = "Invoice Document";

        String filename = UUID.randomUUID().toString();
        if (sFtpSend.send(new ByteArrayInputStream(arr), filename, contract.getInvoiceId())) {
            dataBean.saveDocInfo(contract.getInvoiceId(), docname, contract.getCreationDate(), uuid);
        }


        return gson.toJson(contract);
    }

    private String getContractData(HttpServletRequest request) {
        return null;
    }
}
