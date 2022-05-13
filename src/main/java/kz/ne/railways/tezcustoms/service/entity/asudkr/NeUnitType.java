package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "NSI", name = "NE_UNIT_TYPE")
public class NeUnitType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UNIT_TYPE_UN")
    private Long unitType;

    @Column(name = "UNIT_CODE", length = 3)
    private String unitCode;

    @Column(name = "UNIT_NAME", length = 512)
    private String unitName;

    @Column(name = "UNIT_DESCRIPTION", length = 1024)
    private String unitDescription;

    @Column(name = "UNIT_BGN")
    private Timestamp unitBgn;

    @Column(name = "UNIT_END")
    private Timestamp unitEnd;

    public void setUnitType(Long unitType) {
        this.unitType = unitType;
    }

    public Long getUnitType() {
        return unitType;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitBgn(Timestamp unitBgn) {
        this.unitBgn = unitBgn;
    }

    public Timestamp getUnitBgn() {
        return unitBgn;
    }

    public void setUnitEnd(Timestamp unitEnd) {
        this.unitEnd = unitEnd;
    }

    public Timestamp getUnitEnd() {
        return unitEnd;
    }


}
