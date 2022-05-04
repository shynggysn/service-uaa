package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NE_SMGS_SENDER_INFO", schema = "KTZ")
@NamedQuery(name = "getSmgsSenderInfoByInvoiceId", query = "SELECT n FROM NeSmgsSenderInfo n WHERE n.invUn = :invcUn")
public class NeSmgsSenderInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMGS_SENDER_INFO#UN")
    private Long smgsSenderInfoUn;

    @Column(name = "INV#UN")
    private long invUn;

    @Column(name = "SENDER_CODE")
    private String senderCode;

    @Column(name = "SENDER_TYPE")
    private String senderType;

    @Column(name = "SENDER_NAME")
    private String senderName;

    @Column(name = "SENDER_FULL_NAME")
    private String senderFullName;

    @Column(name = "SENDER_STREET")
    private String senderStreet;

    @Column(name = "SENDER_BUILDING")
    private String senderBuilding;

    @Column(name = "SENDER_SITY")
    private String senderSity;

    @Column(name = "SENDER_REGION", length = 200)
    private String senderRegion;

    @Column(name = "SENDER_POST_INDEX")
    private String senderPostIndex;

    @Column(name = "SENDER_COUNTRY_CODE")
    private String senderCountryCode;

    @Column(name = "SENDER_SIGNATURE")
    private String senderSignature;

    @Column(name = "SENDER_TELEPHONE_NUM")
    private String senderTelephoneNum;

    @Column(name = "SENDER_TELEFAX_NUM")
    private String senderTelefaxNum;

    @Column(name = "SENDER_EMAIL")
    private String senderEmail;

    @Column(name = "SENDER_OKPO")
    private String senderOkpo;

    @Column(name = "SENDER_BIN", length = 50)
    private String senderBin;

    @Column(name = "SENDER_IIN", length = 50)
    private String senderIin;

    @Column(name = "CATEGORY_TYPE")
    private Long categoryType;

    @Column(name = "KATO_TYPE")
    private Long katoType;

    @Column(name = "ITN", length = 25)
    private String itn;

    @Column(name = "KPP", length = 25)
    private String kpp;


    private static final long serialVersionUID = 1L;

    public NeSmgsSenderInfo() {
        super();
    }

    public Long getSmgsSenderInfoUn() {
        return this.smgsSenderInfoUn;
    }

    public void setSmgsSenderInfoUn(Long smgsSenderInfoUn) {
        this.smgsSenderInfoUn = smgsSenderInfoUn;
    }

    public long getInvUn() {
        return this.invUn;
    }

    public void setInvUn(long invUn) {
        this.invUn = invUn;
    }

    public String getSenderCode() {
        return this.senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public String getSenderType() {
        return this.senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderStreet() {
        return this.senderStreet;
    }

    public void setSenderStreet(String senderStreet) {
        this.senderStreet = senderStreet;
    }

    public String getSenderBuilding() {
        return this.senderBuilding;
    }

    public void setSenderBuilding(String senderBuilding) {
        this.senderBuilding = senderBuilding;
    }

    public String getSenderSity() {
        return this.senderSity;
    }

    public void setSenderSity(String senderSity) {
        this.senderSity = senderSity;
    }

    public String getSenderPostIndex() {
        return this.senderPostIndex;
    }

    public void setSenderPostIndex(String senderPostIndex) {
        this.senderPostIndex = senderPostIndex;
    }

    public String getSenderCountryCode() {
        return this.senderCountryCode;
    }

    public void setSenderCountryCode(String senderCountryCode) {
        this.senderCountryCode = senderCountryCode;
    }

    public String getSenderSignature() {
        return this.senderSignature;
    }

    public void setSenderSignature(String senderSignature) {
        this.senderSignature = senderSignature;
    }

    public String getSenderTelephoneNum() {
        return this.senderTelephoneNum;
    }

    public void setSenderTelephoneNum(String senderTelephoneNum) {
        this.senderTelephoneNum = senderTelephoneNum;
    }

    public String getSenderTelefaxNum() {
        return this.senderTelefaxNum;
    }

    public void setSenderTelefaxNum(String senderTelefaxNum) {
        this.senderTelefaxNum = senderTelefaxNum;
    }

    public String getSenderEmail() {
        return this.senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public void setSenderOkpo(String senderOkpo) {
        this.senderOkpo = senderOkpo;
    }

    public String getSenderOkpo() {
        return senderOkpo;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderRegion(String senderRegion) {
        this.senderRegion = senderRegion;
    }

    public String getSenderRegion() {
        return senderRegion;
    }

    public void setSenderBin(String senderBin) {
        this.senderBin = senderBin;
    }

    public String getSenderBin() {
        return senderBin;
    }

    public void setSenderIin(String senderIin) {
        this.senderIin = senderIin;
    }

    public String getSenderIin() {
        return senderIin;
    }

    public void setCategoryType(Long categoryType) {
        this.categoryType = categoryType;
    }

    public Long getCategoryType() {
        return categoryType;
    }

    public void setKatoType(Long katoType) {
        this.katoType = katoType;
    }

    public Long getKatoType() {
        return katoType;
    }

    public void setItn(String itn) {
        this.itn = itn;
    }

    public String getItn() {
        return itn;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getKpp() {
        return kpp;
    }

}
