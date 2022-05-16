package kz.ne.railways.tezcustoms.service.entity.asudkr;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "NE_INVC_REF_PI", schema = "KTZ")
public class NeInvcRefPi implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REF_PI_UN")
    private Long id;

    @Column(name = "INVC_UN")
    private Long invcUn;

    @Column(name = "PREV_INFO_INVC_UN")
    private Long prevInvcUn;

    @Column(name = "ADDED_ON")
    private Timestamp addedOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvcUn() {
        return invcUn;
    }

    public void setInvcUn(Long invcUn) {
        this.invcUn = invcUn;
    }

    public Long getPrevInvcUn() {
        return prevInvcUn;
    }

    public void setPrevInvcUn(Long prevInvcUn) {
        this.prevInvcUn = prevInvcUn;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Timestamp addedOn) {
        this.addedOn = addedOn;
    }
}
