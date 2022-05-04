package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "MANAGEMENT", schema = "NSI")
public class Management implements Serializable {
    @Id
    @Column(name = "MANAG#UN")
    private long managUn;

    @Column(name = "MANAG#ID")
    private int managId;

    @Column(name = "MANAG_NO")
    private short managNo;

    @Column(name = "COU#UN")
    private long couUn;

    @Column(name = "MANAG_NAME")
    private String managName;

    @Column(name = "M_NAME_RUS")
    private String mNameRus;

    @Column(name = "M_NAME_LAT")
    private String mNameLat;

    @Column(name = "MANAG#BGN")
    private Timestamp managBgn;

    @Column(name = "MANAG#END")
    private Timestamp managEnd;

    @Column(name = "SHORT_NAME")
    private String shortName;

    private static final long serialVersionUID = 1L;

    public Management() {
        super();
    }

    public long getManagUn() {
        return this.managUn;
    }

    public void setManagUn(long managUn) {
        this.managUn = managUn;
    }

    public int getManagId() {
        return this.managId;
    }

    public void setManagId(int managId) {
        this.managId = managId;
    }

    public short getManagNo() {
        return this.managNo;
    }

    public void setManagNo(short managNo) {
        this.managNo = managNo;
    }

    public long getCouUn() {
        return this.couUn;
    }

    public void setCouUn(long couUn) {
        this.couUn = couUn;
    }

    public String getManagName() {
        return this.managName;
    }

    public void setManagName(String managName) {
        this.managName = managName;
    }

    public String getMNameRus() {
        return this.mNameRus;
    }

    public void setMNameRus(String mNameRus) {
        this.mNameRus = mNameRus;
    }

    public String getMNameLat() {
        return this.mNameLat;
    }

    public void setMNameLat(String mNameLat) {
        this.mNameLat = mNameLat;
    }

    public Timestamp getManagBgn() {
        return this.managBgn;
    }

    public void setManagBgn(Timestamp managBgn) {
        this.managBgn = managBgn;
    }

    public Timestamp getManagEnd() {
        return this.managEnd;
    }

    public void setManagEnd(Timestamp managEnd) {
        this.managEnd = managEnd;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

}
