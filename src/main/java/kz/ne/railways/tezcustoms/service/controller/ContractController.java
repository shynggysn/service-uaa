package kz.ne.railways.tezcustoms.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kz.ne.railways.tezcustoms.service.payload.request.InvoiceRequest;
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
    private final ContractsService contractsService;


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


}
