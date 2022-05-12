package kz.ne.railways.tezcustoms.service.entity.asudkr;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "NSI", name = "NE_KATO_TYPE")
public class NeKatoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KATO_UN")
    private Long katoUn;

    @Column(name = "KATO_CODE", length = 25)
    private String katoCode;

    @Column(name = "KATO_NAME", length = 512)
    private String katoName;

    @Column(name = "KATO_BGN")
    private Timestamp katoBgn;

    @Column(name = "KATO_END")
    private Timestamp katoEnd;

    public void setKatoUn(Long katoUn) {
        this.katoUn = katoUn;
    }

    public Long getKatoUn() {
        return katoUn;
    }

    public void setKatoCode(String katoCode) {
        this.katoCode = katoCode;
    }

    public String getKatoCode() {
        return katoCode;
    }

    public void setKatoName(String katoName) {
        this.katoName = katoName;
    }

    public String getKatoName() {
        return katoName;
    }

    public void setKatoBgn(Timestamp katoBgn) {
        this.katoBgn = katoBgn;
    }

    public Timestamp getKatoBgn() {
        return katoBgn;
    }

    public void setKatoEnd(Timestamp katoEnd) {
        this.katoEnd = katoEnd;
    }

    public Timestamp getKatoEnd() {
        return katoEnd;
    }



}
