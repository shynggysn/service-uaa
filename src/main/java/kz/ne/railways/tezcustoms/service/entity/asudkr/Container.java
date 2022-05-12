package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "CONTAINER", schema = "NSI")
public class Container implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2394083416378552861L;

    @Id
    @Column(name = "CON_UN")
    private Long conUn;

    @Column(name = "CON_ID")
    private Integer conId;

    @Column(name = "CONGROUP_UN")
    private Long conGroupUn;

    @Column(name = "CON_CODE")
    public String conCode;

    @Column(name = "CON_SIZE")
    private String conSize;

    @Column(name = "CON_CHARACTER")
    private String conCharacter;

    @Column(name = "CON_LENGTH")
    private Integer conLength;

    @Column(name = "CON_HEIGHT")
    private Integer conHeight;

    @Column(name = "CON_WEIGHT")
    private Double conWeight;

    @Column(name = "CON_MAX_WEIGHT")
    private Double conMaxWeight;

    @Column(name = "CON_BGN")
    private Timestamp conBgn;

    @Column(name = "CON_END")
    private Timestamp conEnd;

    public Long getConUn() {
        return conUn;
    }

    public void setConUn(Long conUn) {
        this.conUn = conUn;
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
    }

    public Long getConGroupUn() {
        return conGroupUn;
    }

    public void setConGroupUn(Long conGroupUn) {
        this.conGroupUn = conGroupUn;
    }

    public String getConCode() {
        return conCode;
    }

    public void setConCode(String conCode) {
        this.conCode = conCode;
    }

    public String getConSize() {
        return conSize;
    }

    public void setConSize(String conSize) {
        this.conSize = conSize;
    }

    public String getConCharacter() {
        return conCharacter;
    }

    public void setConCharacter(String conCharacter) {
        this.conCharacter = conCharacter;
    }

    public Integer getConLength() {
        return conLength;
    }

    public void setConLength(Integer conLength) {
        this.conLength = conLength;
    }

    public Integer getConHeight() {
        return conHeight;
    }

    public void setConHeight(Integer conHeight) {
        this.conHeight = conHeight;
    }

    public Double getConWeight() {
        return conWeight;
    }

    public void setConWeight(Double conWeight) {
        this.conWeight = conWeight;
    }

    public Double getConMaxWeight() {
        return conMaxWeight;
    }

    public void setConMaxWeight(Double conMaxWeight) {
        this.conMaxWeight = conMaxWeight;
    }

    public Timestamp getConBgn() {
        return conBgn;
    }

    public void setConBgn(Timestamp conBgn) {
        this.conBgn = conBgn;
    }

    public Timestamp getConEnd() {
        return conEnd;
    }

    public void setConEnd(Timestamp conEnd) {
        this.conEnd = conEnd;
    }


}
