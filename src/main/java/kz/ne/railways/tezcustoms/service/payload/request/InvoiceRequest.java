package kz.ne.railways.tezcustoms.service.payload.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class InvoiceRequest {

    @NotBlank
    private String startSta;

    @NotBlank
    private String destSta;

    private String expCode;

    @NotBlank
    private String invoiceNum;
}
