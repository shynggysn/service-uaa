package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NE_SMGS_DECLARANT_INFO", schema = "KTZ")
@NamedQuery(name = "getSmgsDeclarantInfoByInvoiceId",
                query = "SELECT n FROM NeSmgsDeclarantInfo n WHERE n.invUn = :invcUn")
public class NeSmgsDeclarantInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMGS_DECLARANT_INFO#UN")
    private Long smgsDeclarantInfoUn;

    @Column(name = "INV#UN")
    private long invUn;

    @Column(name = "DECLARANT_NAME")
    private String declarantName; // Наименование/ФИО

    @Column(name = "DECLARANT_SHORT_NAME")
    private String declarantShortName; // Краткое наименование

    @Column(name = "DECLARANT_COUNTRY")
    private String declarantCountry; // Страна

    @Column(name = "DECLARANT_INDEX")
    private String declarantIndex; // Почтовый индекс

    @Column(name = "DECLARANT_CITY")
    private String declarantCity; // Населеннывй пункт

    @Column(name = "DECLARANT_REGION")
    private String declarantRegion; // Область (регион, штат и т.п.)

    @Column(name = "DECLARANT_ADDRESS")
    private String declarantAddress; // улица, номер дома, номер офиса

    @Column(name = "DECLARANT_KZ_BIN")
    private String declarantKZBin; // БИН для РК

    @Column(name = "DECLARANT_KZ_IIN")
    private String declarantKZIin; // ИИН для РК

    @Column(name = "DECLARANT_KZ_PERSONS_CATEGORY")
    private String declarantKZPersonsCategory; // Категория лица для РК

    @Column(name = "DECLARANT_KZ_KATO")
    private String declarantKZKATO; // КАТО для РК

    @Column(name = "DECLARANT_KZ_ITN")
    private String declarantKZITN; // ИТН резерва для РК

    @Column(name = "DECLARANT_RU_OGRN")
    private String declarantRUOGRN; // ОГРН/ОГРНИП для РФ

    @Column(name = "DECLARANT_RU_KPP")
    private String declarantRUKPP; // КПП для РФ

    @Column(name = "DECLARANT_RU_INN")
    private String declarantRUINN; // ИНН для РФ

    @Column(name = "DECLARANT_BY_UNP")
    private String declarantBYUNP; // УНП для Беларуси

    @Column(name = "DECLARANT_BY_IN")
    private String declarantBYIN; // Идентификационный номер физ.лица для Беларуси

    @Column(name = "DECLARANT_AM_UNN")
    private String declarantAMUNN; // УНН для Армении

    @Column(name = "DECLARANT_AM_NZOU")
    private String declarantAMNZOU; // НЗОУ для Армении

    @Column(name = "DECLARANT_KG_INN")
    private String declarantKGINN; // ИНН для Кыргызстана

    @Column(name = "DECLARANT_KG_OKPO")
    private String declarantKGOKPO; // ОКПО для Кыргызстана

    private static final long serialVersionUID = 1L;

    public NeSmgsDeclarantInfo() {
        super();
    }

    public Long getSmgsDeclarantInfoUn() {
        return smgsDeclarantInfoUn;
    }

    public void setSmgsDeclarantInfoUn(Long smgsDeclarantInfoUn) {
        this.smgsDeclarantInfoUn = smgsDeclarantInfoUn;
    }

    public long getInvUn() {
        return invUn;
    }

    public void setInvUn(long invUn) {
        this.invUn = invUn;
    }

    public String getDeclarantName() {
        return declarantName;
    }

    public void setDeclarantName(String declarantName) {
        this.declarantName = declarantName;
    }

    public String getDeclarantShortName() {
        return declarantShortName;
    }

    public void setDeclarantShortName(String declarantShortName) {
        this.declarantShortName = declarantShortName;
    }

    public String getDeclarantCountry() {
        return declarantCountry;
    }

    public void setDeclarantCountry(String declarantCountry) {
        this.declarantCountry = declarantCountry;
    }

    public String getDeclarantIndex() {
        return declarantIndex;
    }

    public void setDeclarantIndex(String declarantIndex) {
        this.declarantIndex = declarantIndex;
    }

    public String getDeclarantCity() {
        return declarantCity;
    }

    public void setDeclarantCity(String declarantCity) {
        this.declarantCity = declarantCity;
    }

    public String getDeclarantRegion() {
        return declarantRegion;
    }

    public void setDeclarantRegion(String declarantRegion) {
        this.declarantRegion = declarantRegion;
    }

    public String getDeclarantAddress() {
        return declarantAddress;
    }

    public void setDeclarantAddress(String declarantAddress) {
        this.declarantAddress = declarantAddress;
    }

    public String getDeclarantKZBin() {
        return declarantKZBin;
    }

    public void setDeclarantKZBin(String declarantKZBin) {
        this.declarantKZBin = declarantKZBin;
    }

    public String getDeclarantKZIin() {
        return declarantKZIin;
    }

    public void setDeclarantKZIin(String declarantKZIin) {
        this.declarantKZIin = declarantKZIin;
    }

    public String getDeclarantKZPersonsCategory() {
        return declarantKZPersonsCategory;
    }

    public void setDeclarantKZPersonsCategory(String declarantKZPersonsCategory) {
        this.declarantKZPersonsCategory = declarantKZPersonsCategory;
    }

    public String getDeclarantKZKATO() {
        return declarantKZKATO;
    }

    public void setDeclarantKZKATO(String declarantKZKATO) {
        this.declarantKZKATO = declarantKZKATO;
    }

    public String getDeclarantKZITN() {
        return declarantKZITN;
    }

    public void setDeclarantKZITN(String declarantKZITN) {
        this.declarantKZITN = declarantKZITN;
    }

    public String getDeclarantRUOGRN() {
        return declarantRUOGRN;
    }

    public void setDeclarantRUOGRN(String declarantRUOGRN) {
        this.declarantRUOGRN = declarantRUOGRN;
    }

    public String getDeclarantRUKPP() {
        return declarantRUKPP;
    }

    public void setDeclarantRUKPP(String declarantRUKPP) {
        this.declarantRUKPP = declarantRUKPP;
    }

    public String getDeclarantRUINN() {
        return declarantRUINN;
    }

    public void setDeclarantRUINN(String declarantRUINN) {
        this.declarantRUINN = declarantRUINN;
    }

    public String getDeclarantBYUNP() {
        return declarantBYUNP;
    }

    public void setDeclarantBYUNP(String declarantBYUNP) {
        this.declarantBYUNP = declarantBYUNP;
    }

    public String getDeclarantBYIN() {
        return declarantBYIN;
    }

    public void setDeclarantBYIN(String declarantBYIN) {
        this.declarantBYIN = declarantBYIN;
    }

    public String getDeclarantAMUNN() {
        return declarantAMUNN;
    }

    public void setDeclarantAMUNN(String declarantAMUNN) {
        this.declarantAMUNN = declarantAMUNN;
    }

    public String getDeclarantAMNZOU() {
        return declarantAMNZOU;
    }

    public void setDeclarantAMNZOU(String declarantAMNZOU) {
        this.declarantAMNZOU = declarantAMNZOU;
    }

    public String getDeclarantKGINN() {
        return declarantKGINN;
    }

    public void setDeclarantKGINN(String declarantKGINN) {
        this.declarantKGINN = declarantKGINN;
    }

    public String getDeclarantKGOKPO() {
        return declarantKGOKPO;
    }

    public void setDeclarantKGOKPO(String declarantKGOKPO) {
        this.declarantKGOKPO = declarantKGOKPO;
    }
}
