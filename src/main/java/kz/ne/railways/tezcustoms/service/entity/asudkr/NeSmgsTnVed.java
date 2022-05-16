package kz.ne.railways.tezcustoms.service.entity.asudkr;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
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

}
