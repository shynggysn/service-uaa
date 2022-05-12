package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "NE_SMGS_CARGO", schema = "KTZ")
@NamedQuery(name = "getSmgsCargoByInvoiceId", query = "SELECT n FROM NeSmgsCargo n WHERE n.invUn = :invcUn")
public class NeSmgsCargo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMGS_CARGO_UN")
    private Long smgsCargoUn;

    @Column(name = "INV_UN")
    private long invUn;

    @Column(name = "PLACE_NUM")
    private Double placeNum;

    @Column(name = "PACKING_CODE")
    private String packingCode;

    @Column(name = "SENDER_COUNTRY")
    private String senderCountry;

    @Column(name = "DEST_COUNTRY")
    private String destCountry;

    @Column(name = "ETSNG_CODE")
    private String etsngCode;

    @Column(name = "GNG_CODE")
    private String gngCode;

    private String conductor;

    @Column(name = "MASS_SPOS_CODE")
    private String massSposCode;

    @Column(name = "MASS_SPOS_NAME")
    private String massSposName;

    @Column(name = "MASS_BRUTTO")
    private BigDecimal massBrutto;


    @Column(name = "FIXED_BY_SECTION")
    private String fixedBySection;

    @Column(name = "SENDER_CARGO_STATMENT")
    private String senderCargoStatment;

    @Column(name = "FIXED_BY_DOC")
    private Integer fixedByDoc;

    @Column(name = "FIXED_BY_CHAPTER")
    private String fixedByChapter;

    @Column(name = "FIXED_BY_PARAGRAPH")
    private String fixedByParagraph;


    private static final long serialVersionUID = 1L;

    public NeSmgsCargo() {
        super();
    }



    public String getSenderCargoStatment() {
        return senderCargoStatment;
    }



    public void setSenderCargoStatment(String senderCargoStatment) {
        this.senderCargoStatment = senderCargoStatment;
    }



    public Long getSmgsCargoUn() {
        return this.smgsCargoUn;
    }

    public void setSmgsCargoUn(Long smgsCargoUn) {
        this.smgsCargoUn = smgsCargoUn;
    }

    public long getInvUn() {
        return this.invUn;
    }

    public void setInvUn(long invUn) {
        this.invUn = invUn;
    }

    public Double getPlaceNum() {
        return this.placeNum;
    }

    public void setPlaceNum(Double placeNum) {
        this.placeNum = placeNum;
    }

    public String getPackingCode() {
        return this.packingCode;
    }

    public void setPackingCode(String packingCode) {
        this.packingCode = packingCode;
    }

    public String getSenderCountry() {
        return this.senderCountry;
    }

    public void setSenderCountry(String senderCountry) {
        this.senderCountry = senderCountry;
    }

    public String getDestCountry() {
        return this.destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry;
    }

    public String getEtsngCode() {
        return this.etsngCode;
    }

    public void setEtsngCode(String etsngCode) {
        this.etsngCode = etsngCode;
    }

    public String getGngCode() {
        return this.gngCode;
    }

    public void setGngCode(String gngCode) {
        this.gngCode = gngCode;
    }

    public String getConductor() {
        return this.conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getMassSposCode() {
        return this.massSposCode;
    }

    public void setMassSposCode(String massSposCode) {
        this.massSposCode = massSposCode;
    }

    public String getMassSposName() {
        return this.massSposName;
    }

    public void setMassSposName(String massSposName) {
        this.massSposName = massSposName;
    }

    public BigDecimal getMassBrutto() {
        return this.massBrutto;
    }

    public void setMassBrutto(BigDecimal massBrutto) {
        this.massBrutto = massBrutto;
    }

    public void setFixedByDoc(Integer fixedByDoc) {
        this.fixedByDoc = fixedByDoc;
    }

    public Integer getFixedByDoc() {
        return fixedByDoc;
    }

    public void setFixedByChapter(String fixedByChapter) {
        this.fixedByChapter = fixedByChapter;
    }

    public String getFixedByChapter() {
        return fixedByChapter;
    }

    public void setFixedByParagraph(String fixedByParagraph) {
        this.fixedByParagraph = fixedByParagraph;
    }

    public String getFixedByParagraph() {
        return fixedByParagraph;
    }

    public void setFixedBySection(String fixedBySection) {
        this.fixedBySection = fixedBySection;
    }

    public String getFixedBySection() {
        return fixedBySection;
    }

}
