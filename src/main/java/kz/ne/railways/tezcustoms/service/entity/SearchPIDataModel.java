package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SearchPIDataModel implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private Date createdDate;
    private String author;
    private String trainIndex;
    private String invoiceNum;
    private String codeGood;
    private String nameGood;
    private String startStation;
    private String destStation;
    private String sender;
    private String receiver;
    private String response;
    private Date responseTime;
    private String status;
    private String numVagonContainer;
    private String containerMark;
    private Date tranzitSendDatetime;
    private int docType;
    private int isContainer;
    private Date invDateTime;
    private String startStaName;
    private String destStaName;
    private String isPi;


    @Transient
    private String hasDoc;

    @Transient
    private boolean isView;


    public String getHasDoc() {
        return hasDoc;
    }

    public void setHasDoc(String hasDoc) {
        this.hasDoc = hasDoc;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTrainIndex(String trainIndex) {
        this.trainIndex = trainIndex;
    }

    public String getTrainIndex() {
        return trainIndex;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setDestStation(String destStation) {
        this.destStation = destStation;
    }

    public String getDestStation() {
        return destStation;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCodeGood(String codeGood) {
        this.codeGood = codeGood;
    }

    public String getCodeGood() {
        return codeGood;
    }

    public void setNameGood(String nameGood) {
        this.nameGood = nameGood;
    }

    public String getNameGood() {
        return nameGood;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setNumVagonContainer(String numVagonContainer) {
        this.numVagonContainer = numVagonContainer;
    }

    public String getNumVagonContainer() {
        return numVagonContainer;
    }

    public Boolean getIsView() {
        return isView;
    }

    public void setIsView(Boolean isView) {
        this.isView = isView;
    }

    public Date getTranzitSendDatetime() {
        return tranzitSendDatetime;
    }

    public void setTranzitSendDatetime(Date tranzitSendDatetime) {
        this.tranzitSendDatetime = tranzitSendDatetime;
    }

    public String getContainerMark() {
        return containerMark;
    }

    public void setContainerMark(String containerMark) {
        this.containerMark = containerMark;
    }

    public int getDocType() {
        return docType;
    }

    public void setDocType(int docType) {
        this.docType = docType;
    }

    public int getIsContainer() {
        return isContainer;
    }

    public void setIsContainer(int isContainer) {
        this.isContainer = isContainer;
    }

    public Date getInvDateTime() {
        return invDateTime;
    }

    public void setInvDateTime(Date invDateTime) {
        this.invDateTime = invDateTime;
    }

    public String getStartStaName() {
        return startStaName;
    }

    public void setStartStaName(String startStaName) {
        this.startStaName = startStaName;
    }

    public String getDestStaName() {
        return destStaName;
    }

    public void setDestStaName(String destStaName) {
        this.destStaName = destStaName;
    }

    public String getIsPi() {
        return isPi;
    }

    public void setIsPi(String isPi) {
        this.isPi = isPi;
    }
}
