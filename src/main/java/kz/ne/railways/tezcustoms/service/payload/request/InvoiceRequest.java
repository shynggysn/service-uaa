package kz.ne.railways.tezcustoms.service.payload.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class InvoiceRequest {

    private String expCode;

    @NotBlank
    private String invoiceNum;

    @NotBlank
    private String date;
}
