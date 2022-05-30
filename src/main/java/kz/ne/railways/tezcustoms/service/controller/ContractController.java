package kz.ne.railways.tezcustoms.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.model.InvoiceData;
import kz.ne.railways.tezcustoms.service.model.transitdeclaration.SaveDeclarationResponseType;
import kz.ne.railways.tezcustoms.service.payload.request.InvoiceRequest;
import kz.ne.railways.tezcustoms.service.model.FormData;
import kz.ne.railways.tezcustoms.service.payload.response.MessageResponse;
import kz.ne.railways.tezcustoms.service.service.ContractsService;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import kz.ne.railways.tezcustoms.service.service.transitdeclaration.TransitDeclarationService;
import kz.ne.railways.tezcustoms.service.util.ExcelReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;


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
    private final ExcelReader excelReader;
    private final TransitDeclarationService td;
    private final ForDataBeanLocal dataBean;


    @Operation(summary = "Load a contract from ASU DKR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract successfully loaded",
                            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content)})
    @PostMapping("load")
    public FormData loadContract(@Valid @RequestBody InvoiceRequest requestDto) throws IOException {
        log.debug("In loadContract...");
        return contractsService.loadContract(requestDto.getStartSta(), requestDto.getDestSta(), requestDto.getExpCode(),
                        requestDto.getInvoiceNum());
    }


    @Operation(summary = "Load Goods from Excel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goods successfully loaded",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/loadFromExcel")
    public ResponseEntity<?> sendToAstana1(@RequestParam("file") MultipartFile file) throws IOException {
        if (ExcelReader.hasExcelFormat(file)){
            InvoiceData invoiceData = excelReader.getInvoiceFromFile(file.getInputStream());
            log.debug("Invoice data: \n" + invoiceData);
            return ResponseEntity.ok(invoiceData);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Please upload an excel file!"));
    }


    //TODO: change method to save Invoice Goods in db
    @Operation(summary = "sends transit declaration to Astana1")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Declaration successfully sent",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/sendtoCustoms")
    public ResponseEntity<?> sendToCustoms(@RequestBody FormData formData) throws IOException {
        log.debug(formData.toString());

        dataBean.saveInvoiceData(formData);

        log.debug("invoiceId is: " + formData.getInvoiceId());

        SaveDeclarationResponseType result = td.send(Long.parseLong(formData.getInvoiceId()));
        log.debug("declaration response result: " + result.toString());

        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(result.getValue()));
    }
}
