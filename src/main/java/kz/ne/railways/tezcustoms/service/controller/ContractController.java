package kz.ne.railways.tezcustoms.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.model.FormData;
import kz.ne.railways.tezcustoms.service.model.InvoiceData;
import kz.ne.railways.tezcustoms.service.model.UserInvoices;
import kz.ne.railways.tezcustoms.service.model.transit_declaration.SaveDeclarationResponseType;
import kz.ne.railways.tezcustoms.service.payload.request.InvoiceRequest;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;
import kz.ne.railways.tezcustoms.service.service.ContractsService;
import kz.ne.railways.tezcustoms.service.service.UserInvoiceService;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import kz.ne.railways.tezcustoms.service.service.transitdeclaration.TransitDeclarationService;
import kz.ne.railways.tezcustoms.service.util.ExcelReader;
import kz.ne.railways.tezcustoms.service.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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

    private final ContractsService contractsService;
    private final ResourceLoader resourceLoader;
    private final UserInvoiceService userInvoiceService;
    private final ExcelReader excelReader;
    private final TransitDeclarationService transitDeclarationService;
    private final ForDataBeanLocal dataBean;


    @Operation(summary = "Load a contract from ASU DKR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract successfully loaded",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = FormData.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content)})
    @PostMapping("load")
    public ResponseEntity<?> loadContract(@Valid @RequestBody InvoiceRequest request) {
        log.debug("In loadContract...");

        try {
        FormData formData = contractsService.loadContract(request.getExpCode(), request.getInvoiceNum());
        if (formData == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Invalid parameters supplied"));
        }
        return ResponseEntity.ok(formData);

        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.badRequest().body(new MessageResponse(exception.getMessage()));
        }
    }

    @GetMapping("/xlsxTemplate")
    public ResponseEntity<?> downloadExcelTemplate () {
        String headerValue = "attachment; filename=template.xlsx";
        try {
            Resource resource = resourceLoader.getResource("classpath:Template.xlsx");
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(resource);
        }
        catch (Exception exception) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse(exception.getMessage()));
        }
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<UserInvoices>> getInvoicesByUser() {
        return ResponseEntity.ok(userInvoiceService.getUserInvoices(SecurityUtils.getCurrentUserId()));
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<FormData> getInvoiceById(@PathVariable Long id) {
        return ResponseEntity.ok(userInvoiceService.getInvoice(id));
    }

    @Operation(summary = "Load Goods from Excel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goods successfully loaded",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/xlsxTemplate")
    public ResponseEntity<?> sendToAstana1(@RequestParam("file") MultipartFile file) throws IOException {
        if (ExcelReader.hasExcelFormat(file)){
            InvoiceData invoiceData = excelReader.getInvoiceFromFile(file.getInputStream());
            log.debug("Invoice data: \n" + invoiceData);
            return ResponseEntity.ok(invoiceData);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please upload an excel file!"));
    }

    //TODO: change method to save InvoiceData fields (except InvoiceRows) in db
    @Operation(summary = "sends transit declaration to Astana1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Declaration successfully sent",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/sendtoCustoms")
    public ResponseEntity<?> sendToCustoms(@RequestBody FormData formData){
        dataBean.saveInvoiceData(formData);
        SaveDeclarationResponseType result = transitDeclarationService.send(Long.parseLong(formData.getInvoiceId()));
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(result.getValue()));
    }
}
