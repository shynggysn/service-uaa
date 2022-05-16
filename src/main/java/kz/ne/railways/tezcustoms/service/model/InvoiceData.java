package kz.ne.railways.tezcustoms.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class InvoiceData {
    private String invoiceNumber;
    private Date invoiceDate;
    private String shipper;
    private String consignee;
    private String total;
    private List<InvoiceRow> invoiceItems = new ArrayList<InvoiceRow>();

    public void addInvoiceItems(InvoiceRow row) {
        invoiceItems.add(row);
    }
}
