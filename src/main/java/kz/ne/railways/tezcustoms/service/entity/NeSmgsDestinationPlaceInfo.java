package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.*;

@Entity
@Table(schema = "KTZ", name = "NE_SMGS_DESTINATION_PLACE_INFO")
public class NeSmgsDestinationPlaceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEST_PLACE_INFO#UN")
    private Long destPlaceInfoUn;

    @Column(name = "INVOICE#UN")
    private Long invoiceUn;

    @Column(name = "DEST_PLACE", length = 1024)
    private String destPlace;

    @Column(name = "DEST_PLACE_STA", length = 6)
    private String destPlaceSta;

    @Column(name = "DEST_PLACE_COUNTRY_CODE", length = 16)
    private String destPlaceCountryCode;

    @Column(name = "DEST_PLACE_INDEX", length = 64)
    private String destPlaceIndex;

    @Column(name = "DEST_PLACE_CITY", length = 512)
    private String destPlaceCity;

    @Column(name = "DEST_PLACE_REGION", length = 200)
    private String destPlaceRegion;

    @Column(name = "DEST_PLACE_STREET", length = 512)
    private String destPlaceStreet;

    @Column(name = "DEST_PLACE_BUILDING", length = 512)
    private String destPlaceBuilding;

    @Column(name = "DEST_PLACE_CUSTOM_ORG#UN")
    private Long destPlaceCustomOrgUn;

    @Column(name = "DEST_PLACE_CUSTOM_CODE", length = 10)
    private String destPlaceCustomCode;

    @Column(name = "DEST_PLACE_CUSTOM_NAME", length = 250)
    private String destPlaceCustomName;

    public void setDestPlaceInfoUn(Long destPlaceInfoUn) {
        this.destPlaceInfoUn = destPlaceInfoUn;
    }

    public Long getDestPlaceInfoUn() {
        return destPlaceInfoUn;
    }

    public void setInvoiceUn(Long invoiceUn) {
        this.invoiceUn = invoiceUn;
    }

    public Long getInvoiceUn() {
        return invoiceUn;
    }

    public void setDestPlace(String destPlace) {
        this.destPlace = destPlace;
    }

    public String getDestPlace() {
        return destPlace;
    }

    public void setDestPlaceSta(String destPlaceSta) {
        this.destPlaceSta = destPlaceSta;
    }

    public String getDestPlaceSta() {
        return destPlaceSta;
    }

    public void setDestPlaceCountryCode(String destPlaceCountryCode) {
        this.destPlaceCountryCode = destPlaceCountryCode;
    }

    public String getDestPlaceCountryCode() {
        return destPlaceCountryCode;
    }

    public void setDestPlaceIndex(String destPlaceIndex) {
        this.destPlaceIndex = destPlaceIndex;
    }

    public String getDestPlaceIndex() {
        return destPlaceIndex;
    }

    public void setDestPlaceCity(String destPlaceCity) {
        this.destPlaceCity = destPlaceCity;
    }

    public String getDestPlaceCity() {
        return destPlaceCity;
    }

    public void setDestPlaceRegion(String destPlaceRegion) {
        this.destPlaceRegion = destPlaceRegion;
    }

    public String getDestPlaceRegion() {
        return destPlaceRegion;
    }

    public void setDestPlaceStreet(String destPlaceStreet) {
        this.destPlaceStreet = destPlaceStreet;
    }

    public String getDestPlaceStreet() {
        return destPlaceStreet;
    }

    public void setDestPlaceBuilding(String destPlaceBuilding) {
        this.destPlaceBuilding = destPlaceBuilding;
    }

    public String getDestPlaceBuilding() {
        return destPlaceBuilding;
    }

    public void setDestPlaceCustomOrgUn(Long destPlaceCustomOrgUn) {
        this.destPlaceCustomOrgUn = destPlaceCustomOrgUn;
    }

    public Long getDestPlaceCustomOrgUn() {
        return destPlaceCustomOrgUn;
    }

    public void setDestPlaceCustomCode(String destPlaceCustomCode) {
        this.destPlaceCustomCode = destPlaceCustomCode;
    }

    public String getDestPlaceCustomCode() {
        return destPlaceCustomCode;
    }

    public void setDestPlaceCustomName(String destPlaceCustomName) {
        this.destPlaceCustomName = destPlaceCustomName;
    }

    public String getDestPlaceCustomName() {
        return destPlaceCustomName;
    }

}
