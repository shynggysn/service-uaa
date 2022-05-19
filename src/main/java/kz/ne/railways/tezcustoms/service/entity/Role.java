package kz.ne.railways.tezcustoms.service.entity;

import kz.ne.railways.tezcustoms.service.model.ERole;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole role;

    public Role() {

    }

    public Role(ERole role) {
        this.role = role;
    }
}
