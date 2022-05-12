package kz.ne.railways.tezcustoms.service.entity.asudkr;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(schema = "KTZ", name = "NE_SMGS_TN_VED")
public class NeSmgsTnVed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SMGS_TN_VED_UN")
    private Long smgsTnVedUn;

    @Column(name = "INVOICE_UN")
    private Long invoiceUn;

    @Column(name = "TN_VED_CODE", length = 25)
    private String tnVedCode;

    @Column(name = "PRODUCT_DESCRIPTION", length = 500)
    private String productDescription;

    @Column(name = "PRODUCT_DESCRIPTION_ADD", length = 500)
    private String productDescriptionAdd;

    @Column(name = "COUNT_BY_UNIT")
    private BigDecimal countByUnit;

    @Column(name = "UNIT_TYPE_UN")
    private Long unitTypeUn;

    @Column(name = "PLACE_CARGO_COUNT")
    private BigDecimal placeCargoCount;

    @Column(name = "PACKING_COUNT")
    private BigDecimal packingCount;

    @Column(name = "PAKAGE_PART_QUANTITY")
    private BigDecimal packagePartQuantity;

    @Column(name = "PACKING_TYPE_UN")
    private Long packingTypeUn;

    @Column(name = "PLACE_CARGO_MARK", length = 512)
    private String placeCargoMark;

    @Column(name = "NETTO_WEIGHT")
    private BigDecimal nettoWeight;

    @Column(name = "BRUTTO_WEIGHT")
    private BigDecimal bruttoWeight;

    @Column(name = "PRICE_BY_ONE")
    private BigDecimal priceByOne;

    @Column(name = "PRICE_BY_FULL")
    private BigDecimal priceByFull;

    @Column(name = "CURRENCY_CODE_UN")
    private Long currencyCodeUn;

    @Column(name = "CONTAINER", length = 2048)
    private String container;

    @Column(name = "TN_VED_NAME", length = 500)
    private String tnVedName;

    @Column(name = "VAGON_ACCESSORY_COU_NO")
    private String vagonAccessoryCouNo;

    @Column(name = "TN_VED_COUNTRY")
    private String tnVedCountry;

    @Column(name = "TN_VED_IS_ARMY")
    private Short tnVedIsArmy;

    public void setSmgsTnVedUn(Long smgsTnVedUn) {
        this.smgsTnVedUn = smgsTnVedUn;
    }

    public Long getSmgsTnVedUn() {
        return smgsTnVedUn;
    }

    public void setInvoiceUn(Long invoiceUn) {
        this.invoiceUn = invoiceUn;
    }

    public Long getInvoiceUn() {
        return invoiceUn;
    }

    public void setTnVedCode(String tnVedCode) {
        this.tnVedCode = tnVedCode;
    }

    public String getTnVedCode() {
        return tnVedCode;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setPackingTypeUn(Long packingTypeUn) {
        this.packingTypeUn = packingTypeUn;
    }

    public Long getPackingTypeUn() {
        return packingTypeUn;
    }

    public void setPlaceCargoMark(String placeCargoMark) {
        this.placeCargoMark = placeCargoMark;
    }

    public String getPlaceCargoMark() {
        return placeCargoMark;
    }



    public void setCurrencyCodeUn(Long currencyCodeUn) {
        this.currencyCodeUn = currencyCodeUn;
    }

    public Long getCurrencyCodeUn() {
        return currencyCodeUn;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public String getContainer() {
        return container;
    }

    public void setCountByUnit(BigDecimal countByUnit) {
        this.countByUnit = countByUnit;
    }

    public BigDecimal getCountByUnit() {
        return countByUnit;
    }

    public void setUnitTypeUn(Long unitTypeUn) {
        this.unitTypeUn = unitTypeUn;
    }

    public Long getUnitTypeUn() {
        return unitTypeUn;
    }

    public void setPlaceCargoCount(BigDecimal placeCargoCount) {
        this.placeCargoCount = placeCargoCount;
    }

    public BigDecimal getPlaceCargoCount() {
        return placeCargoCount;
    }

    public void setPackingCount(BigDecimal packingCount) {
        this.packingCount = packingCount;
    }

    public BigDecimal getPackingCount() {
        return packingCount;
    }

    public void setNettoWeight(BigDecimal nettoWeight) {
        this.nettoWeight = nettoWeight;
    }

    public BigDecimal getNettoWeight() {
        return nettoWeight;
    }

    public void setBruttoWeight(BigDecimal bruttoWeight) {
        this.bruttoWeight = bruttoWeight;
    }

    public BigDecimal getBruttoWeight() {
        return bruttoWeight;
    }

    public void setPriceByOne(BigDecimal priceByOne) {
        this.priceByOne = priceByOne;
    }

    public BigDecimal getPriceByOne() {
        return priceByOne;
    }

    public void setPriceByFull(BigDecimal priceByFull) {
        this.priceByFull = priceByFull;
    }

    public BigDecimal getPriceByFull() {
        return priceByFull;
    }

    public void setTnVedName(String tnVedName) {
        this.tnVedName = tnVedName;
    }

    public String getTnVedName() {
        return tnVedName;
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

    public void setPackagePartQuantity(BigDecimal packagePartQuantity) {
        this.packagePartQuantity = packagePartQuantity;
    }

    public BigDecimal getPackagePartQuantity() {
        return packagePartQuantity;
    }

    public String getProductDescriptionAdd() {
        return productDescriptionAdd;
    }

    public void setProductDescriptionAdd(String productDescriptionAdd) {
        this.productDescriptionAdd = productDescriptionAdd;
    }



}
