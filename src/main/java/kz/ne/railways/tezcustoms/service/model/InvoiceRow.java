package kz.ne.railways.tezcustoms.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InvoiceRow {
    private String name;
    private String code;
    private String unit;
    private String quantity;
    private String netto;
    private String brutto;
    private String price;

    public InvoiceRow() {
    }
}
