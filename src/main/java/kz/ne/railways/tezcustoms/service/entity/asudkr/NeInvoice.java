package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "NE_INVOICE", schema = "KTZ")
public class NeInvoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVC_UN")
    private Long invcUn;

    @Column(name = "INVC_NUM")
    private String invcNum;

    @Column(name = "INVC_DT")
    private Timestamp invcDt;

    @Column(name = "INVC_SPEED_UN")
    private Long invcSpeedUn;

    @Column(name = "INVC_MASS_UN")
    private Long invcMassUn;

    @Column(name = "INVC_LOADING_BY_MEANS")
    private Long invcLoadingByMeansUn;

    @Column(name = "INVC_STATUS")
    private int invcStatus;

    @Column(name = "INV_DATE")
    private Date invDate;

    @Column(name = "MGS_ID")
    private String mgsId;

    @Column(name = "OTPR_NO")
    private Long otprNo;

    @Column(name = "SMGS_TYPE")
    private Integer smgsType;

    @Column(name = "DATE_PRIEM")
    private Timestamp datePriem;

    @Column(name = "LOADED_BY")
    private Integer loadedBy;

    @Column(name = "CARGO_WORTH")
    private BigDecimal cargoWorth;

    @Column(name = "CARGO_WORTH_CURR")
    private String cargoWorthCurr;

    @Column(name = "SHIPPING_STATUS")
    private String shippingStatus;

    @Column(name = "ADDITION_INFO")
    private String additionInfo;

    @Column(name = "ADDITION_INFO_ROAD")
    private String additionInfoRoad;

    @Column(name = "INFO_NOT_FOR_ROAD")
    private String infoNotForRoad;

    @Column(name = "SENDER_STATMENT")
    private String senderStatment;

    @Column(name = "TARIF_NOTES")
    private String tarifNotes;

    @Column(name = "CARRIER_NOTES")
    private String carrierNotes;

    @Column(name = "NOT_PAPER_SHIPPING")
    private Short notPaperShipping;

    @Column(name = "CONTRACT_NUM")
    private String contractNum;

    @Column(name = "RECIVE_STATION_CODE")
    private String reciveStationCode;

    @Column(name = "DEST_STATION_CODE")
    private String destStationCode;

    @Column(name = "FOREGN_BORDER_POINT")
    private String foregnBorderPoint;

    @Column(name = "BORDER_POINT_INBOUND")
    private String borderPointInbound;

    @Column(name = "BORDER_POINT_OUTBOUND")
    private String borderPointOutbound;

    @Column(name = "IS_PRIVATE_ROAD")
    private Integer isPrivateRoad;

    @Column(name = "INVOICE_DATETIME")
    private Timestamp invoiceDatetime;

    @Column(name = "MESSAGE_STATUS")
    private Integer messageStatus;

    @Column(name = "PARENT_INVC_UN")
    private Long parentInvcUn;

    @Column(name = "PERIOD_OF_DELIVERY")
    private BigDecimal periodOfDelivery;

    @Column(name = "DISTANCE")
    private BigDecimal distance;

    @Column(name = "INVOICE_RASKREDITATION_DT")
    private Timestamp invoiceRaskreditationDt;

    @Column(name = "UNLOAD_DT")
    private Timestamp invUnloadDt;

    @Column(name = "TRAIN_INDEX", length = 16)
    private String trainIndex;

    @Column(name = "IS_CONTAINER")
    private byte isContainer;

    @Column(name = "DOC_TYPE")
    private int docType;

    private static final Long serialVersionUID = 1L;

    public NeInvoice() {
        super();
    }

    public void setInvcUn(Long invcUn) {
        this.invcUn = invcUn;
    }

    public Long getInvcUn() {
        return invcUn;
    }

    public void setInvcNum(String invcNum) {
        this.invcNum = invcNum;
    }

    public String getInvcNum() {
        return invcNum;
    }

    public void setInvcDt(Timestamp invcDt) {
        this.invcDt = invcDt;
    }

    public Timestamp getInvcDt() {
        return invcDt;
    }

    public void setInvcSpeedUn(Long invcSpeedUn) {
        this.invcSpeedUn = invcSpeedUn;
    }

    public Long getInvcSpeedUn() {
        return invcSpeedUn;
    }

    public void setInvcMassUn(Long invcMassUn) {
        this.invcMassUn = invcMassUn;
    }

    public Long getInvcMassUn() {
        return invcMassUn;
    }

    public void setInvcLoadingByMeansUn(Long invcLoadingByMeansUn) {
        this.invcLoadingByMeansUn = invcLoadingByMeansUn;
    }

    public Long getInvcLoadingByMeansUn() {
        return invcLoadingByMeansUn;
    }

    public void setInvcStatus(Integer invcStatus) {
        this.invcStatus = invcStatus;
    }

    public Integer getInvcStatus() {
        return invcStatus;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public Date getInvDate() {
        return invDate;
    }

    public String getMgsId() {
        return this.mgsId;
    }

    public void setMgsId(String mgsId) {
        this.mgsId = mgsId;
    }

    public Long getOtprNo() {
        return this.otprNo;
    }

    public void setOtprNo(Long otprNo) {
        this.otprNo = otprNo;
    }

    public Integer getSmgsType() {
        return this.smgsType;
    }

    public void setSmgsType(Integer smgsType) {
        this.smgsType = smgsType;
    }

    public Timestamp getDatePriem() {
        return this.datePriem;
    }

    public void setDatePriem(Timestamp datePriem) {
        this.datePriem = datePriem;
    }

    public Integer getLoadedBy() {
        return this.loadedBy;
    }

    public void setLoadedBy(Integer loadedBy) {
        this.loadedBy = loadedBy;
    }

    public BigDecimal getCargoWorth() {
        return this.cargoWorth;
    }

    public void setCargoWorth(BigDecimal cargoWorth) {
        this.cargoWorth = cargoWorth;
    }

    public String getCargoWorthCurr() {
        return this.cargoWorthCurr;
    }

    public void setCargoWorthCurr(String cargoWorthCurr) {
        this.cargoWorthCurr = cargoWorthCurr;
    }

    public String getShippingStatus() {
        return this.shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getAdditionInfo() {
        return this.additionInfo;
    }

    public void setAdditionInfo(String additionInfo) {
        this.additionInfo = additionInfo;
    }

    public String getAdditionInfoRoad() {
        return this.additionInfoRoad;
    }

    public void setAdditionInfoRoad(String additionInfoRoad) {
        this.additionInfoRoad = additionInfoRoad;
    }

    public String getInfoNotForRoad() {
        return this.infoNotForRoad;
    }

    public void setInfoNotForRoad(String infoNotForRoad) {
        this.infoNotForRoad = infoNotForRoad;
    }

    public String getSenderStatment() {
        return this.senderStatment;
    }

    public void setSenderStatment(String senderStatment) {
        this.senderStatment = senderStatment;
    }

    public String getTarifNotes() {
        return this.tarifNotes;
    }

    public void setTarifNotes(String tarifNotes) {
        this.tarifNotes = tarifNotes;
    }

    public String getCarrierNotes() {
        return this.carrierNotes;
    }

    public void setCarrierNotes(String carrierNotes) {
        this.carrierNotes = carrierNotes;
    }

    public Short getNotPaperShipping() {
        return this.notPaperShipping;
    }

    public void setNotPaperShipping(Short notPaperShipping) {
        this.notPaperShipping = notPaperShipping;
    }

    public String getContractNum() {
        return this.contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getReciveStationCode() {
        return this.reciveStationCode;
    }

    public void setReciveStationCode(String reciveStationCode) {
        this.reciveStationCode = reciveStationCode;
    }

    public String getDestStationCode() {
        return this.destStationCode;
    }

    public void setDestStationCode(String destStationCode) {
        this.destStationCode = destStationCode;
    }

    public String getForegnBorderPoint() {
        return this.foregnBorderPoint;
    }

    public void setForegnBorderPoint(String foregnBorderPoint) {
        this.foregnBorderPoint = foregnBorderPoint;
    }

    public String getBorderPointInbound() {
        return this.borderPointInbound;
    }

    public void setBorderPointInbound(String borderPointInbound) {
        this.borderPointInbound = borderPointInbound;
    }

    public String getBorderPointOutbound() {
        return this.borderPointOutbound;
    }

    public void setBorderPointOutbound(String borderPointOutbound) {
        this.borderPointOutbound = borderPointOutbound;
    }

    public void setIsPrivateRoad(Integer isPrivateRoad) {
        this.isPrivateRoad = isPrivateRoad;
    }

    public Integer getIsPrivateRoad() {
        return isPrivateRoad;
    }

    public void setInvoiceDatetime(Timestamp invoiceDatetime) {
        this.invoiceDatetime = invoiceDatetime;
    }

    public Timestamp getInvoiceDatetime() {
        return invoiceDatetime;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setParentInvcUn(Long parentInvcUn) {
        this.parentInvcUn = parentInvcUn;
    }

    public Long getParentInvcUn() {
        return parentInvcUn;
    }

    public BigDecimal getPeriodOfDelivery() {
        return periodOfDelivery;
    }

    public void setPeriodOfDelivery(BigDecimal periodOfDelivery) {
        this.periodOfDelivery = periodOfDelivery;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public void setInvoiceRaskreditationDt(Timestamp invoiceRaskreditationDt) {
        this.invoiceRaskreditationDt = invoiceRaskreditationDt;
    }

    public Timestamp getInvoiceRaskreditationDt() {
        return invoiceRaskreditationDt;
    }

    public Timestamp getInvUnloadDt() {
        return invUnloadDt;
    }

    public void setInvUnloadDt(Timestamp invUnloadDt) {
        this.invUnloadDt = invUnloadDt;
    }

    public void setIsContainer(byte isContainer) {
        this.isContainer = isContainer;
    }

    public byte getIsContainer() {
        return isContainer;
    }

    public void setTrainIndex(String trainIndex) {
        this.trainIndex = trainIndex;
    }

    public String getTrainIndex() {
        return trainIndex;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public int getDocType() {
        return docType;
    }


}
