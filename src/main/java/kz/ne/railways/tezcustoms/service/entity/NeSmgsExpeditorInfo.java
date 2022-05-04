package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NE_SMGS_EXPEDITOR_INFO", schema = "KTZ")
@NamedQuery(name = "getSmgsExpeditorInfoByInvoiceId",
                query = "SELECT n FROM NeSmgsExpeditorInfo n WHERE n.invUn = :invcUn")
public class NeSmgsExpeditorInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMGS_EXPEDITOR_INFO#UN")
    private Long smgsExpeditorInfoUn;

    @Column(name = "INV#UN")
    private long invUn;

    @Column(name = "EXPEDITOR_NAME")
    private String expeditorName; // Наименование/ФИО

    @Column(name = "EXPEDITOR_SHORT_NAME")
    private String expeditorShortName; // Краткое наименование

    @Column(name = "EXPEDITOR_COUNTRY")
    private String expeditorCountry; // Страна

    @Column(name = "EXPEDITOR_INDEX")
    private String expeditorIndex; // Почтовый индекс

    @Column(name = "EXPEDITOR_CITY")
    private String expeditorCity; // Населеннывй пункт

    @Column(name = "EXPEDITOR_REGION")
    private String expeditorRegion; // Область (регион, штат и т.п.)

    @Column(name = "EXPEDITOR_ADDRESS")
    private String expeditorAddress; // улица, номер дома, номер офиса

    @Column(name = "EXPEDITOR_KZ_BIN")
    private String expeditorKZBin; // БИН для РК

    @Column(name = "EXPEDITOR_KZ_IIN")
    private String expeditorKZIin; // ИИН для РК

    @Column(name = "EXPEDITOR_KZ_PERSONS_CATEGORY")
    private String expeditorKZPersonsCategory; // Категория лица для РК

    @Column(name = "EXPEDITOR_KZ_KATO")
    private String expeditorKZKATO; // КАТО для РК

    @Column(name = "EXPEDITOR_KZ_ITN")
    private String expeditorKZITN; // ИТН резерва для РК

    @Column(name = "EXPEDITOR_RU_OGRN")
    private String expeditorRUOGRN; // ОГРН/ОГРНИП для РФ

    @Column(name = "EXPEDITOR_RU_KPP")
    private String expeditorRUKPP; // КПП для РФ

    @Column(name = "EXPEDITOR_RU_INN")
    private String expeditorRUINN; // ИНН для РФ

    @Column(name = "EXPEDITOR_BY_UNP")
    private String expeditorBYUNP; // УНП для Беларуси

    @Column(name = "EXPEDITOR_BY_IN")
    private String expeditorBYIN; // Идентификационный номер физ.лица для Беларуси

    @Column(name = "EXPEDITOR_AM_UNN")
    private String expeditorAMUNN; // УНН для Армении

    @Column(name = "EXPEDITOR_AM_NZOU")
    private String expeditorAMNZOU; // НЗОУ для Армении

    @Column(name = "EXPEDITOR_KG_INN")
    private String expeditorKGINN; // ИНН для Кыргызстана

    @Column(name = "EXPEDITOR_KG_OKPO")
    private String expeditorKGOKPO; // ОКПО для Кыргызстана

    private static final long serialVersionUID = 1L;

    public NeSmgsExpeditorInfo() {
        super();
    }

    public Long getSmgsExpeditorInfoUn() {
        return smgsExpeditorInfoUn;
    }

    public void setSmgsExpeditorInfoUn(Long smgsExpeditorInfoUn) {
        this.smgsExpeditorInfoUn = smgsExpeditorInfoUn;
    }

    public long getInvUn() {
        return invUn;
    }

    public void setInvUn(long invUn) {
        this.invUn = invUn;
    }

    public String getExpeditorName() {
        return expeditorName;
    }

    public void setExpeditorName(String expeditorName) {
        this.expeditorName = expeditorName;
    }

    public String getExpeditorShortName() {
        return expeditorShortName;
    }

    public void setExpeditorShortName(String expeditorShortName) {
        this.expeditorShortName = expeditorShortName;
    }

    public String getExpeditorCountry() {
        return expeditorCountry;
    }

    public void setExpeditorCountry(String expeditorCountry) {
        this.expeditorCountry = expeditorCountry;
    }

    public String getExpeditorIndex() {
        return expeditorIndex;
    }

    public void setExpeditorIndex(String expeditorIndex) {
        this.expeditorIndex = expeditorIndex;
    }

    public String getExpeditorCity() {
        return expeditorCity;
    }

    public void setExpeditorCity(String expeditorCity) {
        this.expeditorCity = expeditorCity;
    }

    public String getExpeditorRegion() {
        return expeditorRegion;
    }

    public void setExpeditorRegion(String expeditorRegion) {
        this.expeditorRegion = expeditorRegion;
    }

    public String getExpeditorAddress() {
        return expeditorAddress;
    }

    public void setExpeditorAddress(String expeditorAddress) {
        this.expeditorAddress = expeditorAddress;
    }

    public String getExpeditorKZBin() {
        return expeditorKZBin;
    }

    public void setExpeditorKZBin(String expeditorKZBin) {
        this.expeditorKZBin = expeditorKZBin;
    }

    public String getExpeditorKZIin() {
        return expeditorKZIin;
    }

    public void setExpeditorKZIin(String expeditorKZIin) {
        this.expeditorKZIin = expeditorKZIin;
    }

    public String getExpeditorKZPersonsCategory() {
        return expeditorKZPersonsCategory;
    }

    public void setExpeditorKZPersonsCategory(String expeditorKZPersonsCategory) {
        this.expeditorKZPersonsCategory = expeditorKZPersonsCategory;
    }

    public String getExpeditorKZKATO() {
        return expeditorKZKATO;
    }

    public void setExpeditorKZKATO(String expeditorKZKATO) {
        this.expeditorKZKATO = expeditorKZKATO;
    }

    public String getExpeditorKZITN() {
        return expeditorKZITN;
    }

    public void setExpeditorKZITN(String expeditorKZITN) {
        this.expeditorKZITN = expeditorKZITN;
    }

    public String getExpeditorRUOGRN() {
        return expeditorRUOGRN;
    }

    public void setExpeditorRUOGRN(String expeditorRUOGRN) {
        this.expeditorRUOGRN = expeditorRUOGRN;
    }

    public String getExpeditorRUKPP() {
        return expeditorRUKPP;
    }

    public void setExpeditorRUKPP(String expeditorRUKPP) {
        this.expeditorRUKPP = expeditorRUKPP;
    }

    public String getExpeditorRUINN() {
        return expeditorRUINN;
    }

    public void setExpeditorRUINN(String expeditorRUINN) {
        this.expeditorRUINN = expeditorRUINN;
    }

    public String getExpeditorBYUNP() {
        return expeditorBYUNP;
    }

    public void setExpeditorBYUNP(String expeditorBYUNP) {
        this.expeditorBYUNP = expeditorBYUNP;
    }

    public String getExpeditorBYIN() {
        return expeditorBYIN;
    }

    public void setExpeditorBYIN(String expeditorBYIN) {
        this.expeditorBYIN = expeditorBYIN;
    }

    public String getExpeditorAMUNN() {
        return expeditorAMUNN;
    }

    public void setExpeditorAMUNN(String expeditorAMUNN) {
        this.expeditorAMUNN = expeditorAMUNN;
    }

    public String getExpeditorAMNZOU() {
        return expeditorAMNZOU;
    }

    public void setExpeditorAMNZOU(String expeditorAMNZOU) {
        this.expeditorAMNZOU = expeditorAMNZOU;
    }

    public String getExpeditorKGINN() {
        return expeditorKGINN;
    }

    public void setExpeditorKGINN(String expeditorKGINN) {
        this.expeditorKGINN = expeditorKGINN;
    }

    public String getExpeditorKGOKPO() {
        return expeditorKGOKPO;
    }

    public void setExpeditorKGOKPO(String expeditorKGOKPO) {
        this.expeditorKGOKPO = expeditorKGOKPO;
    }
}
