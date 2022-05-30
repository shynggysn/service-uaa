package kz.ne.railways.tezcustoms.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.model.UserInvoices;
import kz.ne.railways.tezcustoms.service.payload.request.InvoiceRequest;
import kz.ne.railways.tezcustoms.service.model.FormData;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;
import kz.ne.railways.tezcustoms.service.service.ContractsService;
import kz.ne.railways.tezcustoms.service.service.UserInvoiceService;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import kz.ne.railways.tezcustoms.service.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.List;


@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class ContractController {

    /*
     * TODO make general encoding request.setCharacterEncoding("UTF-8");
     * response.setCharacterEncoding("UTF-8");
     */

    private static final long serialVersionUID = 1L;

    private final ForDataBeanLocal dataBean;
    private final ContractsService contractsService;
    private final ResourceLoader resourceLoader;
    private final UserInvoiceService userInvoiceService;


    @Operation(summary = "Load a contract from ASU DKR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract successfully loaded",
                            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content)})
    @PostMapping("load")
    public ResponseEntity<?> loadContract(@Valid @RequestBody InvoiceRequest requestDto) {
        log.debug("In loadContract...");
        try {
        FormData formData = contractsService.loadContract(requestDto.getStartSta(),
                        requestDto.getDestSta(), requestDto.getExpCode(), requestDto.getInvoiceNum());
        if (formData == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid parameters supplied"));
        }
        return ResponseEntity.ok(formData);

        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
        }
    }

    @GetMapping("/getXlsxTemplate")
    public ResponseEntity<?> downloadExcelTemplate () {
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=template.xlsx";
        try {
            FileSystemResource file = new FileSystemResource(new File(String.valueOf(resourceLoader.getResource("classpath:Template.xlsx").getInputStream())));
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(file);
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
        }
    }

    @GetMapping("/getInvoices")
    public ResponseEntity<List<UserInvoices>> getInvoices() {
        return ResponseEntity.ok(userInvoiceService.getUserInvoices(SecurityUtils.getCurrentUserId()));
    }

}
