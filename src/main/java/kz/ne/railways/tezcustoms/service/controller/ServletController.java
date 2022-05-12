package kz.ne.railways.tezcustoms.service.controller;

import com.google.gson.Gson;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.entity.User;
import kz.ne.railways.tezcustoms.service.exception.ResourceNotFoundException;
import kz.ne.railways.tezcustoms.service.model.Contract;
import kz.ne.railways.tezcustoms.service.model.FormData;
import kz.ne.railways.tezcustoms.service.entity.asudkr.NeSmgsAdditionDocuments;
import kz.ne.railways.tezcustoms.service.payload.request.EcpSignRequest;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;
import kz.ne.railways.tezcustoms.service.repository.RoleRepository;
import kz.ne.railways.tezcustoms.service.repository.UserRepository;
import kz.ne.railways.tezcustoms.service.service.EcpService;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import kz.ne.railways.tezcustoms.service.service.transitdeclaration.TransitDeclarationService;
import kz.ne.railways.tezcustoms.service.util.ExcelReader;
import kz.ne.railways.tezcustoms.service.util.HttpUtil;
import kz.ne.railways.tezcustoms.service.util.SFtpSend;
import kz.ne.railways.tezcustoms.service.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.customs.information.customsdocuments.esadout_cu._5_11.ESADoutCUType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/servlet")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ServletController {

    /* TODO make general encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
   */

    private static final long serialVersionUID = 1L;
    private static final String METHOD = "method";

    private final ForDataBeanLocal dataBean;
    private final HttpUtil httpUtil;
    private final SFtpSend sFtpSend;

    private Gson gson = new Gson();

    private final ResourceLoader resourceLoader;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EcpService ecpService;
    private final ExcelReader excelReader;
    private final TransitDeclarationService td;

    @Operation(summary = "Sign ecp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document successfully signed",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "NOT_VALID_SIGNER: IIN/BIN is not allowed to sign this document",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @PostMapping("sign-ecp-data")
    //@PreAuthorize("hasRole('CLIENT') or hasRole('OPERATOR') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity signEcpData(@RequestBody EcpSignRequest ecpSignRequest) {
        try {
            User user = userRepository.findByEmail(SecurityUtils.getCurrentUserLogin())
                            .orElseThrow(() -> new ResourceNotFoundException("User not found."));
            boolean isValid = ecpService.isValidSigner(ecpSignRequest.getSignedData(), user);
            if (isValid) {
                return ResponseEntity.ok(new MessageResponse("Document successfully signed"));
            } else {
                String errorCode = "NOT_VALID_SIGNER";
                String message = "IIN/BIN is not allowed to sign this document";
                return ResponseEntity.badRequest().body(new MessageResponse(message, errorCode));
            }
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
        }
    }

    @PostMapping("/sendToAstana1")
    public void sendToAstana1(@RequestBody String invNum) throws IOException {
        FormData formData = dataBean.getContractData(invNum);

        formData.setInvoiceData(excelReader.getInvoiceFromFile());

        String name = "Altair";
        String surname = "Aimenov";

        td.send(formData.getInvoiceId(), name, surname);

        log.debug(formData.getRecieverIIN());
    }

}
