package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NE_INVOICE_INFO")
@NamedQuery(name = "getNeInvoiceInfoByInvoiceId",
                query = "SELECT n FROM NeInvoiceInfo n WHERE n.invoiceUn = :invoiceUn")
public class NeInvoiceInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVOICE_INFO_UN")
    private Long invoiceInfoUn;

    @Column(name = "INVOICE_UN")
    private long invoiceUn;

    @Column(name = "TRAFFIC_BY_ROUTES")
    private String trafficByRoutes;

    @Column(name = "IS_DIVIDE")
    private Short isDivide;

    @Column(name = "DIFFUSE_STATION_ST_UN")
    private Long diffuseStationStUn;

    @Column(name = "REQ_TYPE_TRAF_UN")
    private Long reqTypeTrafUn;

    @Column(name = "SHIP_KIND_UN")
    private Long shipKindUn;

    @Column(name = "REQ_PLAN")
    private String reqPlan;

    @Column(name = "GU12_PLAN_NR")
    private String gu12PlanNr;

    @Column(name = "GU11_PLAN_NR")
    private String gu11PlanNr;

    @Column(name = "PROPERTY_UN")
    private Long propertyUn;

    private static final long serialVersionUID = 1L;

    public NeInvoiceInfo() {
        super();
    }

    public Long getInvoiceInfoUn() {
        return this.invoiceInfoUn;
    }

    public void setInvoiceInfoUn(Long invoiceInfoUn) {
        this.invoiceInfoUn = invoiceInfoUn;
    }

    public long getInvoiceUn() {
        return this.invoiceUn;
    }

    public void setInvoiceUn(long invoiceUn) {
        this.invoiceUn = invoiceUn;
    }

    public String getTrafficByRoutes() {
        return this.trafficByRoutes;
    }

    public void setTrafficByRoutes(String trafficByRoutes) {
        this.trafficByRoutes = trafficByRoutes;
    }

    public Short getIsDivide() {
        return this.isDivide;
    }

    public void setIsDivide(Short isDivide) {
        this.isDivide = isDivide;
    }

    public Long getDiffuseStationStUn() {
        return this.diffuseStationStUn;
    }

    public void setDiffuseStationStUn(Long diffuseStationStUn) {
        this.diffuseStationStUn = diffuseStationStUn;
    }

    public Long getReqTypeTrafUn() {
        return this.reqTypeTrafUn;
    }

    public void setReqTypeTrafUn(Long reqTypeTrafUn) {
        this.reqTypeTrafUn = reqTypeTrafUn;
    }

    public Long getShipKindUn() {
        return this.shipKindUn;
    }

    public void setShipKindUn(Long shipKindUn) {
        this.shipKindUn = shipKindUn;
    }

    public String getReqPlan() {
        return this.reqPlan;
    }

    public void setReqPlan(String reqPlan) {
        this.reqPlan = reqPlan;
    }

    public String getGu12PlanNr() {
        return this.gu12PlanNr;
    }

    public void setGu12PlanNr(String gu12PlanNr) {
        this.gu12PlanNr = gu12PlanNr;
    }

    public String getGu11PlanNr() {
        return this.gu11PlanNr;
    }

    public void setGu11PlanNr(String gu11PlanNr) {
        this.gu11PlanNr = gu11PlanNr;
    }

    public Long getPropertyUn() {
        return this.propertyUn;
    }

    public void setPropertyUn(Long propertyUn) {
        this.propertyUn = propertyUn;
    }

}
