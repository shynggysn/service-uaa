package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "COUNTRY", schema = "NSI")
public class Country implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1721762568269063141L;

    @Id
    @Column(name = "COU#UN")
    private Long couUn;

    @Column(name = "COU#ID")
    private int couId;

    @Column(name = "COUNTRY_NO")
    private String countryNo;

    @Column(name = "REGION#UN")
    private int regionUn;

    @Column(name = "COUNTRY_ID")
    private String countryId;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Column(name = "COUNTRY_FULLNAME")
    private String countryFullName;

    @Column(name = "COUNTRY_ID3")
    private String countryId3;

    @Column(name = "COU#BGN")
    private Timestamp couBegin;

    @Column(name = "COU#END")
    private Timestamp couEnd;

    public Long getCouUn() {
        return couUn;
    }

    public void setCouUn(Long couUn) {
        this.couUn = couUn;
    }

    public int getCouId() {
        return couId;
    }

    public void setCouId(int couId) {
        this.couId = couId;
    }

    public String getCountryNo() {
        return countryNo;
    }

    public void setCountryNo(String countryNo) {
        this.countryNo = countryNo;
    }

    public int getRegionUn() {
        return regionUn;
    }

    public void setRegionUn(int regionUn) {
        this.regionUn = regionUn;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryFullName() {
        return countryFullName;
    }

    public void setCountryFullName(String countryFullName) {
        this.countryFullName = countryFullName;
    }

    public String getCountryId3() {
        return countryId3;
    }

    public void setCountryId3(String countryId3) {
        this.countryId3 = countryId3;
    }

    public Timestamp getCouBegin() {
        return couBegin;
    }

    public void setCouBegin(Timestamp couBegin) {
        this.couBegin = couBegin;
    }

    public Timestamp getCouEnd() {
        return couEnd;
    }

    public void setCouEnd(Timestamp couEnd) {
        this.couEnd = couEnd;
    }


}
