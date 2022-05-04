package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "NE_INVOICE_PREV_INFO", schema = "KTZ")
public class NeInvoicePrevInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PREV_INFO#UN")
    private Long prevInfoUn;

    @Column(name = "INVOICE#UN")
    private Long invoiceUn;

    @Column(name = "PREV_INFO_TYPE", length = 1)
    private String prevInfoType; // AppoPrInf - js name

    @Column(name = "ARRIVE_DATETIME")
    private Timestamp arriveTime;

    @Column(name = "ARRIVE_STA_NO", length = 6)
    private String arriveStaNo;

    @Column(name = "CUSTOM_ORG#UN")
    private Long customOrgUn;

    @Column(name = "CUSTOM_CODE", length = 10)
    private String customCode;

    @Column(name = "CUSTOM_NAME", length = 250)
    private String customName;

    @Column(name = "USER#UN")
    private Long userUn;

    @Column(name = "CREATE_DATETIME")
    private Timestamp createDatetime;

    @Column(name = "RESPONSE_DATETIME")
    private Timestamp responseDatetime;

    @Column(name = "PREV_INFO_STATUS")
    private int prevInfoStatus = 0;

    @Column(name = "RESPONSE_TEXT")
    private String responseText;

    @Column(name = "RESPONSE_UUID")
    private String responseUUID;

    @Column(name = "START_STA_COU_NO")
    private String startStaCouNo;

    @Column(name = "DEST_STATION_COU_NO")
    private String destStationCouNo;

    @Column(name = "PREV_INFO_FEATURES")
    private Integer prevInfoFeatures;

    @Column(name = "TRANZIT_SEND_DATETIME")
    private Timestamp tranzitSendDatetime;

    public Timestamp getTranzitSendDatetime() {
        return tranzitSendDatetime;
    }

    public void setTranzitSendDatetime(Timestamp tranzitSendDatetime) {
        this.tranzitSendDatetime = tranzitSendDatetime;
    }

    public String getStartStaCouNo() {
        return startStaCouNo;
    }

    public void setStartStaCouNo(String startStaCouNo) {
        this.startStaCouNo = startStaCouNo;
    }

    public String getDestStationCouNo() {
        return destStationCouNo;
    }

    public void setDestStationCouNo(String destStationCouNo) {
        this.destStationCouNo = destStationCouNo;
    }

    public void setPrevInfoUn(Long prevInfoUn) {
        this.prevInfoUn = prevInfoUn;
    }

    public Long getPrevInfoUn() {
        return prevInfoUn;
    }

    public void setInvoiceUn(Long invoiceUn) {
        this.invoiceUn = invoiceUn;
    }

    public Long getInvoiceUn() {
        return invoiceUn;
    }

    public void setPrevInfoType(String prevInfoType) {
        this.prevInfoType = prevInfoType;
    }

    public String getPrevInfoType() {
        return prevInfoType;
    }

    public void setArriveStaNo(String arriveStaNo) {
        this.arriveStaNo = arriveStaNo;
    }

    public String getArriveStaNo() {
        return arriveStaNo;
    }

    public void setCustomOrgUn(Long customOrgUn) {
        this.customOrgUn = customOrgUn;
    }

    public Long getCustomOrgUn() {
        return customOrgUn;
    }

    public void setCustomCode(String customCode) {
        this.customCode = customCode;
    }

    public String getCustomCode() {
        return customCode;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getCustomName() {
        return customName;
    }

    public void setArriveTime(Timestamp arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Timestamp getArriveTime() {
        return arriveTime;
    }

    public void setUserUn(Long userUn) {
        this.userUn = userUn;
    }

    public Long getUserUn() {
        return userUn;
    }

    public void setCreateDatetime(Timestamp createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Timestamp getCreateDatetime() {
        return createDatetime;
    }

    public void setResponseDatetime(Timestamp responseDatetime) {
        this.responseDatetime = responseDatetime;
    }

    public Timestamp getResponseDatetime() {
        return responseDatetime;
    }

    public void setPrevInfoStatus(int prevInfoStatus) {
        this.prevInfoStatus = prevInfoStatus;
    }

    public int getPrevInfoStatus() {
        return prevInfoStatus;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getResponseUUID() {
        return responseUUID;
    }

    public void setResponseUUID(String responseUUID) {
        this.responseUUID = responseUUID;
    }

    public void setPrevInfoFeatures(Integer prevInfoFeatures) {
        this.prevInfoFeatures = prevInfoFeatures;
    }

    public Integer getPrevInfoFeatures() {
        return prevInfoFeatures;
    }
}
