package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(schema = "KTZ", name = "NE_VAGON_GROUP")
public class NeVagonGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VAG_GROUP_UN")
    private long vagGroupUn;

    @Column(name = "SENDER_UN")
    private Long senderUn;

    @Column(name = "DATE_PODACH")
    private Date datePodach;

    @Column(name = "VAG_GROUP_STATUS_UN")
    private Long vagGroupStatusUn;

    @Column(name = "ST_UN")
    private Long stUn;


    /*
     * @Column(name="CONSIGNOR_UN") private Long consignorUn;
     * 
     * @Column(name="DEST_ST_UN") private Long destStUn;
     * 
     * @Column(name="VAG_GROUP_NO") private String vagGroupNo;
     * 
     * @Column(name="AGR_STATUS") private Long agrStatus;
     * 
     * @Column(name="AGR_COMMENT") private String agrComment;
     * 
     * @Column(name="AGR_TIME") private Timestamp agrTime;
     * 
     * @Column(name="DATETIME_PODACH") private Timestamp dateTimePodach;
     * 
     * @Column(name="TO_CHECK") private Long toCheck;
     */

    public void setDatePodach(Date datePodach) {
        this.datePodach = datePodach;
    }

    public Long getVagGroupStatusUn() {
        return vagGroupStatusUn;
    }

    public void setVagGroupStatusUn(Long vagGroupStatusUn) {
        this.vagGroupStatusUn = vagGroupStatusUn;
    }

    public NeVagonGroup() {
        super();
    }

    public long getVagGroupUn() {
        return this.vagGroupUn;
    }

    public void setVagGroupUn(long vagGroupUn) {
        this.vagGroupUn = vagGroupUn;
    }

    public Long getSenderUn() {
        return senderUn;
    }

    public void setSenderUn(Long senderUn) {
        this.senderUn = senderUn;
    }

    public Long getStUn() {
        return stUn;
    }

    public void setStUn(Long stUn) {
        this.stUn = stUn;
    }

    /*
     * public Long getConsignorUn() { return consignorUn; }
     * 
     * public void setConsignorUn(Long consignorUn) { this.consignorUn = consignorUn; }
     * 
     * public Long getDestStUn() { return destStUn; }
     * 
     * public void setDestStUn(Long destStUn) { this.destStUn = destStUn; }
     * 
     * public String getVagGroupNo() { return vagGroupNo; }
     * 
     * public void setVagGroupNo(String vagGroupNo) { this.vagGroupNo = vagGroupNo; }
     * 
     * public Long getAgrStatus() { return agrStatus; }
     * 
     * public void setAgrStatus(Long agrStatus) { this.agrStatus = agrStatus; }
     * 
     * public String getAgrComment() { return agrComment; }
     * 
     * public void setAgrComment(String agrComment) { this.agrComment = agrComment; }
     * 
     * public Timestamp getAgrTime() { return agrTime; }
     * 
     * public void setAgrTime(Timestamp agrTime) { this.agrTime = agrTime; }
     * 
     * public Timestamp getDateTimePodach() { return dateTimePodach; }
     * 
     * public void setDateTimePodach(Timestamp dateTimePodach) { this.dateTimePodach = dateTimePodach; }
     * 
     * public Long getToCheck() { return toCheck; }
     * 
     * public void setToCheck(Long toCheck) { this.toCheck = toCheck; }
     */
}
