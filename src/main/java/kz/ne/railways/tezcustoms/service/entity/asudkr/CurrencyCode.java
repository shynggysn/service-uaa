package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "CURRENCY_CODE", schema = "NSI")
public class CurrencyCode implements Serializable {
    @Id
    @Column(name = "CUR_CODE_UN")
    private long curCodeUn;

    @Column(name = "CUR_CODE_ID")
    private int curCodeId;

    @Column(name = "CUR_CODE_NAME")
    private String curCodeName;

    @Column(name = "CUR_CODE")
    private String curCode;

    @Column(name = "CUR_CODE_LET")
    private String curCodeLet;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CUR_CODE_BGN")
    private Timestamp curCodeBgn;

    @Column(name = "CUR_CODE_END")
    private Timestamp curCodeEnd;

    private static final long serialVersionUID = 1L;

    public CurrencyCode() {
        super();
    }

    public long getCurCodeUn() {
        return this.curCodeUn;
    }

    public void setCurCodeUn(long curCodeUn) {
        this.curCodeUn = curCodeUn;
    }

    public int getCurCodeId() {
        return this.curCodeId;
    }

    public void setCurCodeId(int curCodeId) {
        this.curCodeId = curCodeId;
    }

    public String getCurCodeName() {
        return this.curCodeName;
    }

    public void setCurCodeName(String curCodeName) {
        this.curCodeName = curCodeName;
    }

    public String getCurCode() {
        return this.curCode;
    }

    public void setCurCode(String curCode) {
        this.curCode = curCode;
    }

    public String getCurCodeLet() {
        return this.curCodeLet;
    }

    public void setCurCodeLet(String curCodeLet) {
        this.curCodeLet = curCodeLet;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Timestamp getCurCodeBgn() {
        return this.curCodeBgn;
    }

    public void setCurCodeBgn(Timestamp curCodeBgn) {
        this.curCodeBgn = curCodeBgn;
    }

    public Timestamp getCurCodeEnd() {
        return this.curCodeEnd;
    }

    public void setCurCodeEnd(Timestamp curCodeEnd) {
        this.curCodeEnd = curCodeEnd;
    }

}
