package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "STA", schema = "NSI")
public class Sta implements Serializable {
    @Id
    @Column(name = "ST_UN")
    private Long stUn;

    @Column(name = "ST_ID")
    private int stId;

    @Column(name = "STA_NO")
    private String staNo;

    @Column(name = "STA_NAME")
    private String staName;

    @Column(name = "DIV_UN")
    private Long divUn;

    @Column(name = "ROAD_UN")
    private Long roadUn;

    @Column(name = "MANAG_UN")
    private Long managUn;

    @Column(name = "PR_UN")
    private Long prUn;

    @Column(name = "TEHPD_UN")
    private Long tehpdUn;

    private Short sign;

    private String paragraph;

    @Column(name = "STA_EMAIL")
    private String staEmail;

    @Column(name = "ST_BGN")
    private Timestamp stBgn;

    @Column(name = "ST_END")
    private Timestamp stEnd;

    private static final Long serialVersionUID = 1L;

    public Sta() {
        super();
    }

    public Long getStUn() {
        return this.stUn;
    }

    public void setStUn(Long stUn) {
        this.stUn = stUn;
    }

    public int getStId() {
        return this.stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    public String getStaNo() {
        return this.staNo;
    }

    public void setStaNo(String staNo) {
        this.staNo = staNo;
    }

    public String getStaName() {
        return this.staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    public Long getDivUn() {
        return this.divUn;
    }

    public void setDivUn(Long divUn) {
        this.divUn = divUn;
    }

    public Long getRoadUn() {
        return this.roadUn;
    }

    public void setRoadUn(Long roadUn) {
        this.roadUn = roadUn;
    }

    public Long getManagUn() {
        return this.managUn;
    }

    public void setManagUn(Long managUn) {
        this.managUn = managUn;
    }

    public Long getPrUn() {
        return this.prUn;
    }

    public void setPrUn(Long prUn) {
        this.prUn = prUn;
    }

    public Long getTehpdUn() {
        return this.tehpdUn;
    }

    public void setTehpdUn(Long tehpdUn) {
        this.tehpdUn = tehpdUn;
    }

    public Short getSign() {
        return this.sign;
    }

    public void setSign(Short sign) {
        this.sign = sign;
    }

    public String getParagraph() {
        return this.paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public String getStaEmail() {
        return this.staEmail;
    }

    public void setStaEmail(String staEmail) {
        this.staEmail = staEmail;
    }

    public Timestamp getStBgn() {
        return this.stBgn;
    }

    public void setStBgn(Timestamp stBgn) {
        this.stBgn = stBgn;
    }

    public Timestamp getStEnd() {
        return this.stEnd;
    }

    public void setStEnd(Timestamp stEnd) {
        this.stEnd = stEnd;
    }

}
