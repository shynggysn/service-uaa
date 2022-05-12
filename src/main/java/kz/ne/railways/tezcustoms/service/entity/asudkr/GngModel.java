package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GngModel {
    @Id
    private int id;
    private String invoiceUn;
    private String code;
    private String shortName1;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setInvoiceUn(String invoiceUn) {
        this.invoiceUn = invoiceUn;
    }

    public String getInvoiceUn() {
        return invoiceUn;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setShortName1(String shortName1) {
        this.shortName1 = shortName1;
    }

    public String getShortName1() {
        return shortName1;
    }
}
