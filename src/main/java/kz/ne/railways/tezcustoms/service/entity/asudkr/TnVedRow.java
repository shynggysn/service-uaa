package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class TnVedRow {
    @Id
    private Long id;
    private Long invoiceUn;
    private String tnVedCode;
    private String description;
    private String descriptionAdditionaly;
    private String tnVedName;
    private BigDecimal countByUnit;
    private Long unitTypeUn;
    private String unitTypeName;
    private BigDecimal placeCargoCount;
    private BigDecimal packingCount;
    private BigDecimal pakagePartQuantity;
    private String packingCode;
    private Long pakingTypeUn;
    private String packingTypeName;
    private String placeCargoMark;
    private BigDecimal netto;
    private BigDecimal brutto;
    private BigDecimal priceByOne;
    private BigDecimal priceByTotal;
    private Long currencyUn;
    private String currencyCode;
    private String currencyName;
    private String container;
    private String documents;
    private String vagonAccessoryCouNo;
    private String tnVedCountry;
    private Short tnVedIsArmy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceUn() {
        return invoiceUn;
    }

    public void setInvoiceUn(Long invoiceUn) {
        this.invoiceUn = invoiceUn;
    }

    public String getTnVedCode() {
        return tnVedCode;
    }

    public void setTnVedCode(String tnVedCode) {
        this.tnVedCode = tnVedCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCountByUnit() {
        return countByUnit;
    }

    public void setCountByUnit(BigDecimal countByUnit) {
        this.countByUnit = countByUnit;
    }

    public Long getUnitTypeUn() {
        return unitTypeUn;
    }

    public void setUnitTypeUn(Long unitTypeUn) {
        this.unitTypeUn = unitTypeUn;
    }

    public String getUnitTypeName() {
        return unitTypeName;
    }

    public void setUnitTypeName(String unitTypeName) {
        this.unitTypeName = unitTypeName;
    }

    public BigDecimal getPlaceCargoCount() {
        return placeCargoCount;
    }

    public void setPlaceCargoCount(BigDecimal placeCargoCount) {
        this.placeCargoCount = placeCargoCount;
    }

    public BigDecimal getPackingCount() {
        return packingCount;
    }

    public void setPackingCount(BigDecimal packingCount) {
        this.packingCount = packingCount;
    }

    public Long getPakingTypeUn() {
        return pakingTypeUn;
    }

    public void setPakingTypeUn(Long pakingTypeUn) {
        this.pakingTypeUn = pakingTypeUn;
    }

    public String getPackingTypeName() {
        return packingTypeName;
    }

    public void setPackingTypeName(String packingTypeName) {
        this.packingTypeName = packingTypeName;
    }

    public String getPlaceCargoMark() {
        return placeCargoMark;
    }

    public void setPlaceCargoMark(String placeCargoMark) {
        this.placeCargoMark = placeCargoMark;
    }

    public BigDecimal getNetto() {
        return netto;
    }

    public void setNetto(BigDecimal netto) {
        this.netto = netto;
    }

    public BigDecimal getBrutto() {
        return brutto;
    }

    public void setBrutto(BigDecimal brutto) {
        this.brutto = brutto;
    }

    public BigDecimal getPriceByOne() {
        return priceByOne;
    }

    public void setPriceByOne(BigDecimal priceByOne) {
        this.priceByOne = priceByOne;
    }

    public BigDecimal getPriceByTotal() {
        return priceByTotal;
    }

    public void setPriceByTotal(BigDecimal priceByTotal) {
        this.priceByTotal = priceByTotal;
    }

    public Long getCurrencyUn() {
        return currencyUn;
    }

    public void setCurrencyUn(Long currencyUn) {
        this.currencyUn = currencyUn;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public void setTnVedName(String tnVedName) {
        this.tnVedName = tnVedName;
    }

    public String getTnVedName() {
        return tnVedName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPackingCode() {
        return packingCode;
    }

    public void setPackingCode(String packingCode) {
        this.packingCode = packingCode;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public void setVagonAccessoryCouNo(String vagonAccessoryCouNo) {
        this.vagonAccessoryCouNo = vagonAccessoryCouNo;
    }

    public String getVagonAccessoryCouNo() {
        return vagonAccessoryCouNo;
    }

    public void setTnVedCountry(String tnVedCountry) {
        this.tnVedCountry = tnVedCountry;
    }

    public String getTnVedCountry() {
        return tnVedCountry;
    }

    public void setTnVedIsArmy(Short tnVedIsArmy) {
        this.tnVedIsArmy = tnVedIsArmy;
    }

    public Short getTnVedIsArmy() {
        return tnVedIsArmy;
    }

    public void setPakagePartQuantity(BigDecimal pakagePartQuantity) {
        this.pakagePartQuantity = pakagePartQuantity;
    }

    public BigDecimal getPakagePartQuantity() {
        return pakagePartQuantity;
    }

    public String getDescriptionAdditionaly() {
        return descriptionAdditionaly;
    }

    public void setDescriptionAdditionaly(String descriptionAdditionaly) {
        this.descriptionAdditionaly = descriptionAdditionaly;
    }



}
