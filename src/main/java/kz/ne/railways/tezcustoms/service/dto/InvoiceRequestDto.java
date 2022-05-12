package kz.ne.railways.tezcustoms.service.dto;

import lombok.Data;

@Data
public class InvoiceRequestDto {
    private String startSta;
    private String destSta;
    private String expCode;
    private String invoiceNum;
}
