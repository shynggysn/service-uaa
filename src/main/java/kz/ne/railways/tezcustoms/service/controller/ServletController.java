package kz.ne.railways.tezcustoms.service.controller;

import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.entity.User;
import kz.ne.railways.tezcustoms.service.exception.ResourceNotFoundException;
import kz.ne.railways.tezcustoms.service.model.FormData;
import kz.ne.railways.tezcustoms.service.model.InvoiceData;
import kz.ne.railways.tezcustoms.service.model.transitdeclaration.SaveDeclarationResponseType;
import kz.ne.railways.tezcustoms.service.model.transit_declaration.SaveDeclarationResponseType;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.*;

@Slf4j
@RestController
@RequestMapping("/servlet")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ServletController {

    /*
     * TODO make general encoding request.setCharacterEncoding("UTF-8");
     * response.setCharacterEncoding("UTF-8");
     */

    private static final long serialVersionUID = 1L;

    private final UserRepository userRepository;
    private final EcpService ecpService;

    @Operation(summary = "Sign ecp")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Document successfully signed",
                            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                            description = "NOT_VALID_SIGNER: IIN/BIN is not allowed to sign this document",
                            content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @PostMapping("sign-ecp-data")
    //@PreAuthorize("hasRole('CLIENT') or hasRole('OPERATOR') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity signEcpData(@Valid @RequestBody EcpSignRequest ecpSignRequest) {
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


    @Operation(summary = "sends transit declaration to Astana1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Declaration successfully sent",
                    content = {@Content(mediaType = "application/json")})
            })
    @PostMapping("/sendToAstana1")
    public ResponseEntity<MessageResponse> sendToAstana1(@RequestParam("invNum")String invNum, @RequestParam("file") MultipartFile file) throws IOException {
        if (ExcelReader.hasExcelFormat(file)){
            FormData formData = dataBean.getContractData(invNum);
            log.debug(formData.toString());

            dataBean.saveInvoiceData(excelReader.getInvoiceFromFile(file.getInputStream()), Long.parseLong(formData.getInvoiceId()));

            log.debug("invoiceId is: " + formData.getInvoiceId());
            SaveDeclarationResponseType result = td.send(Long.parseLong(formData.getInvoiceId()));

            log.debug("declaration response result: " + result.toString());
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(result.getValue()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please upload an excel file!"));
    }

}
