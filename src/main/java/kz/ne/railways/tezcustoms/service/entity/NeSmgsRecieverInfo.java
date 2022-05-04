package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NE_SMGS_RECIEVER_INFO", schema = "KTZ")
@NamedQuery(name = "getSmgsRecieverInfoByInvoiceId",
                query = "SELECT n FROM NeSmgsRecieverInfo n WHERE n.invUn = :invcUn")
public class NeSmgsRecieverInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMGS_RECIEVER_INFO#UN")
    private Long smgsRecieverInfoUn;

    @Column(name = "INV#UN")
    private long invUn;

    @Column(name = "RECIEVER_CODE")
    private String recieverCode;

    @Column(name = "RECIEVER_TYPE")
    private String recieverType;

    @Column(name = "RECIEVER_NAME")
    private String recieverName;

    @Column(name = "RECIEVER_FULL_NAME")
    private String recieverFullName;

    @Column(name = "RECIEVER_STREET")
    private String recieverStreet;

    @Column(name = "RECIEVER_BUILDING")
    private String recieverBuilding;

    @Column(name = "RECIEVER_SITY")
    private String recieverSity;

    @Column(name = "RECIEVER_REGION", length = 200)
    private String recieverRegion;

    @Column(name = "RECIEVER_POST_INDEX")
    private String recieverPostIndex;

    @Column(name = "RECIEVER_COUNTRY_CODE")
    private String recieverCountryCode;

    @Column(name = "RECIEVER_SIGNATURE")
    private String recieverSignature;

    @Column(name = "RECIEVER_TELEPHONE_NUM")
    private String recieverTelephoneNum;

    @Column(name = "RECIEVER_TELEFAX_NUM")
    private String recieverTelefaxNum;

    @Column(name = "RECIEVER_EMAIL")
    private String recieverEmail;

    @Column(name = "RECIEVER_RAILWAY_NUM")
    private String railwayNum;

    @Column(name = "RECIEVER_OKPO")
    private String recieverOkpo;

    @Column(name = "RECIEVER_BIN", length = 50)
    private String recieverBin;

    @Column(name = "RECIEVER_IIN", length = 50)
    private String recieverIin;

    @Column(name = "CATEGORY_TYPE")
    private Long categoryType;

    @Column(name = "KATO_TYPE")
    private Long katoType;

    @Column(name = "ITN", length = 25)
    private String itn;

    @Column(name = "KPP", length = 25)
    private String kpp;

    private static final long serialVersionUID = 1L;

    public NeSmgsRecieverInfo() {
        super();
    }

    public Long getSmgsRecieverInfoUn() {
        return this.smgsRecieverInfoUn;
    }

    public void setSmgsRecieverInfoUn(Long smgsRecieverInfoUn) {
        this.smgsRecieverInfoUn = smgsRecieverInfoUn;
    }

    public long getInvUn() {
        return this.invUn;
    }

    public void setInvUn(long invUn) {
        this.invUn = invUn;
    }

    public String getRecieverCode() {
        return this.recieverCode;
    }

    public void setRecieverCode(String recieverCode) {
        this.recieverCode = recieverCode;
    }

    public String getRecieverType() {
        return this.recieverType;
    }

    public void setRecieverType(String recieverType) {
        this.recieverType = recieverType;
    }

    public String getRecieverName() {
        return this.recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public String getRecieverStreet() {
        return this.recieverStreet;
    }

    public void setRecieverStreet(String recieverStreet) {
        this.recieverStreet = recieverStreet;
    }

    public String getRecieverBuilding() {
        return this.recieverBuilding;
    }

    public void setRecieverBuilding(String recieverBuilding) {
        this.recieverBuilding = recieverBuilding;
    }

    public String getRecieverSity() {
        return this.recieverSity;
    }

    public void setRecieverSity(String recieverSity) {
        this.recieverSity = recieverSity;
    }

    public String getRecieverPostIndex() {
        return this.recieverPostIndex;
    }

    public void setRecieverPostIndex(String recieverPostIndex) {
        this.recieverPostIndex = recieverPostIndex;
    }

    public String getRecieverCountryCode() {
        return this.recieverCountryCode;
    }

    public void setRecieverCountryCode(String recieverCountryCode) {
        this.recieverCountryCode = recieverCountryCode;
    }

    public String getRecieverSignature() {
        return this.recieverSignature;
    }

    public void setRecieverSignature(String recieverSignature) {
        this.recieverSignature = recieverSignature;
    }

    public String getRecieverTelephoneNum() {
        return this.recieverTelephoneNum;
    }

    public void setRecieverTelephoneNum(String recieverTelephoneNum) {
        this.recieverTelephoneNum = recieverTelephoneNum;
    }

    public String getRecieverTelefaxNum() {
        return this.recieverTelefaxNum;
    }

    public void setRecieverTelefaxNum(String recieverTelefaxNum) {
        this.recieverTelefaxNum = recieverTelefaxNum;
    }

    public String getRecieverEmail() {
        return this.recieverEmail;
    }

    public void setRecieverEmail(String recieverEmail) {
        this.recieverEmail = recieverEmail;
    }

    public String getRailwayNum() {
        return railwayNum;
    }

    public void setRailwayNum(String railwayNum) {
        this.railwayNum = railwayNum;
    }

    public String getRecieverOkpo() {
        return recieverOkpo;
    }

    public void setRecieverOkpo(String recieverOkpo) {
        this.recieverOkpo = recieverOkpo;
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


    public void setRecieverRegion(String recieverRegion) {
        this.recieverRegion = recieverRegion;
    }

    public String getRecieverRegion() {
        return recieverRegion;
    }

    public void setRecieverBin(String recieverBin) {
        this.recieverBin = recieverBin;
    }

    public String getRecieverBin() {
        return recieverBin;
    }

    public void setRecieverIin(String recieverIin) {
        this.recieverIin = recieverIin;
    }

    public String getRecieverIin() {
        return recieverIin;
    }

    public void setRecieverFullName(String recieverFullName) {
        this.recieverFullName = recieverFullName;
    }

    public String getRecieverFullName() {
        return recieverFullName;
    }



}
