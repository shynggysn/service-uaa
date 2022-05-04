package kz.ne.railways.tezcustoms.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "NE_VESSEL", schema = "NSI")
public class NeVessel implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -1721762568269063141L;

    @Id
    @Column(name = "NE_VESSEL#UN")
    private Long neVesselUn;


    @Column(name = "VESSEL_NAME")
    private String vesselName;


    @Column(name = "VESSEL_BGN")
    private Timestamp vesselBgn;

    @Column(name = "VESSEL_END")
    private Timestamp vesselEnd;

    public Long getNeVesselUn() {
        return neVesselUn;
    }

    public void setNeVesselUn(Long neVesselUn) {
        this.neVesselUn = neVesselUn;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public Timestamp getVesselBgn() {
        return vesselBgn;
    }

    public void setVesselBgn(Timestamp vesselBgn) {
        this.vesselBgn = vesselBgn;
    }

    public Timestamp getVesselEnd() {
        return vesselEnd;
    }

    public void setVesselEnd(Timestamp vesselEnd) {
        this.vesselEnd = vesselEnd;
    }



}
