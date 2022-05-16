package kz.ne.railways.tezcustoms.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.LocalDatabase;
import kz.ne.railways.tezcustoms.service.dto.InvoiceRequestDto;
import kz.ne.railways.tezcustoms.service.entity.asudkr.NeSmgsAdditionDocuments;
import kz.ne.railways.tezcustoms.service.model.Contract;
import kz.ne.railways.tezcustoms.service.model.FormData;
import kz.ne.railways.tezcustoms.service.service.ContractsService;
import kz.ne.railways.tezcustoms.service.service.bean.ForDataBeanLocal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private final ForDataBeanLocal dataBean;
    private final LocalDatabase localDatabase;
    private final ContractsService contractsService;

    @Operation(summary = "Get a contracts list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the contracts list",
                            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contracts not found", content = @Content)})
    @GetMapping("")
    public String getContracts() {
        return dataBean.getContracts();
    }

    @Operation(summary = "Load a contract from ASU DKR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contract successfully loaded",
                            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Contract not found", content = @Content)})
    @PostMapping("load")
    public FormData loadContract(@Valid @RequestBody InvoiceRequestDto requestDto) throws IOException {
        log.debug("In loadContract...");
        return contractsService.loadContract(requestDto.getStartSta(), requestDto.getDestSta(), requestDto.getExpCode(),
                        requestDto.getInvoiceNum());
    }

    @Operation(summary = "Check logs in console")
    @GetMapping("check")
    private void check() {
        log.debug("Contracts:");
        for (Contract contract : localDatabase.contractList)
            log.debug(contract.getInvoiceId());

        log.debug("\nDocuments:");
        for (NeSmgsAdditionDocuments document : localDatabase.documents)
            log.debug(document.getInvUn() + "");
    }

}
