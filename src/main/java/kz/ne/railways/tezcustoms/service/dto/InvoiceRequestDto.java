package kz.ne.railways.tezcustoms.service.dto;

import lombok.Data;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

import javax.validation.constraints.NotBlank;

@Data
public class InvoiceRequestDto {

    @NotBlank
    private String startSta;

    @NotBlank
    private String destSta;

    private String expCode;

    @NotBlank
    private String invoiceNum;
}
