package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "NE_SMGS_SHIP_LIST", schema = "KTZ")
public class NeSmgsShipList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NE_SMGS_SHIP_LIST_UN")
    private Long neSmgsShipListUn;

    @Column(name = "INV_UN")
    private long invUn;

    @Column(name = "NE_VESSEL_UN")
    private Long neVesselUn;



    private static final long serialVersionUID = 1L;

    public NeSmgsShipList() {
        super();
    }

    public Long getNeSmgsShipListUn() {
        return neSmgsShipListUn;
    }

    public void setNeSmgsShipListUn(Long neSmgsShipListUn) {
        this.neSmgsShipListUn = neSmgsShipListUn;
    }

    public long getInvUn() {
        return invUn;
    }

    public void setInvUn(long invUn) {
        this.invUn = invUn;
    }

    public Long getNeVesselUn() {
        return neVesselUn;
    }

    public void setNeVesselUn(Long neVesselUn) {
        this.neVesselUn = neVesselUn;
    }



}
