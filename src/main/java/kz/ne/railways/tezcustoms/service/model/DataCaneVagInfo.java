package kz.ne.railways.tezcustoms.service.model;

import java.math.BigDecimal;

public class DataCaneVagInfo {
    private String VagNo;
    private String VagNoDC;
    private Integer propertyDC;
    private Integer OwnerCodeDC;
    private Integer depoDC;
    private String OwnerNameDC;
    private Integer typeVagDC;
    private Integer taraDC;
    private Integer railwayNoDC;
    private Integer gpDC;
    private Integer axisCount;
    private BigDecimal kodArMg;
    private String YellowMileage;
    private String RedMileage;

    public String getVagNo() {
        return VagNo;
    }

    public void setVagNo(String vagNo) {
        VagNo = vagNo;
    }

    public String getVagNoDC() {
        return VagNoDC;
    }

    public void setVagNoDC(String vagNoDC) {
        VagNoDC = vagNoDC;
    }

    public Integer getPropertyDC() {
        return propertyDC;
    }

    public void setPropertyDC(Integer propertyDC) {
        this.propertyDC = propertyDC;
    }

    public Integer getOwnerCodeDC() {
        return OwnerCodeDC;
    }

    public void setOwnerCodeDC(Integer ownerCodeDC) {
        OwnerCodeDC = ownerCodeDC;
    }

    public Integer getDepoDC() {
        return depoDC;
    }

    public void setDepoDC(Integer depoDC) {
        this.depoDC = depoDC;
    }

    public String getOwnerNameDC() {
        return OwnerNameDC;
    }

    public void setOwnerNameDC(String ownerNameDC) {
        OwnerNameDC = ownerNameDC;
    }

    public Integer getTypeVagDC() {
        return typeVagDC;
    }

    public void setTypeVagDC(Integer typeVagDC) {
        this.typeVagDC = typeVagDC;
    }

    public Integer getTaraDC() {
        return taraDC;
    }

    public void setTaraDC(Integer taraDC) {
        this.taraDC = taraDC;
    }

    public Integer getRailwayNoDC() {
        return railwayNoDC;
    }

    public void setRailwayNoDC(Integer railwayNoDC) {
        this.railwayNoDC = railwayNoDC;
    }

    public Integer getGpDC() {
        return gpDC;
    }

    public void setGpDC(Integer gpDC) {
        this.gpDC = gpDC;
    }

    /**
     * @param kodArMg the kodArMg to set
     */
    public void setKodArMg(BigDecimal kodArMg) {
        this.kodArMg = kodArMg;
    }

    /**
     * @return the kodArMg
     */
    public BigDecimal getKodArMg() {
        return kodArMg;
    }

    public void setAxisCount(Integer axisCount) {
        this.axisCount = axisCount;
    }

    public Integer getAxisCount() {
        return axisCount;
    }

    public String getYellowMileage() {
        return YellowMileage;
    }

    public void setYellowMileage(String yellowMileage) {
        YellowMileage = yellowMileage;
    }

    public String getRedMileage() {
        return RedMileage;
    }

    public void setRedMileage(String redMileage) {
        RedMileage = redMileage;
    }

}
