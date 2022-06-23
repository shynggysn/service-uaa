package kz.ne.railways.tezcustoms.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Getter
@Setter
@Table(name = "tn_ved", schema = "TEZ")
public class TnVed extends AbstractAuditingEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column
    private Integer level;

    @Column
    private String code;

    @Column(name = "code_ex")
    private String codeEx;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(columnDefinition = "TEXT", name = "text_ex")
    private String textEx;

    @Column
    private String unit;

    @Column(name = "unit_code")
    private String unitCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private TnVed parent;

}
