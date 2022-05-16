package kz.ne.railways.tezcustoms.service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
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

    @Override
    public String toString() {
        return "InvoiceData{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", invoiceDate=" + invoiceDate +
                ", shipper='" + shipper + '\'' +
                ", consignee='" + consignee + '\'' +
                ", total='" + total + '\'' +
                ", invoiceItems=" + invoiceItems +
                '}';
    }
}
