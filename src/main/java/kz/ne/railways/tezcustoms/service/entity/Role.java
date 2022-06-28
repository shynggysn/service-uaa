package kz.ne.railways.tezcustoms.service.entity;

import kz.ne.railways.tezcustoms.service.model.ERole;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles", schema = "TEZ")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }
}
