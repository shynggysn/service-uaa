package kz.ne.railways.tezcustoms.service.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "iinBin"),
        @UniqueConstraint(columnNames = "email")})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 12)
    private String iinBin;

    @NotBlank
    @Size(max = 50)
    private String phone;

    @Size(max = 250)
    private String address;

    @Size(max = 120)
    private String companyName;

    @Size(max = 120)
    private String companyDirector;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Size(max = 50)
    private String middleName;

    @Column(columnDefinition = "boolean default false")
    private boolean isCompany;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastModifiedDate;

    @Size(max = 10)
    private String kato;

    @Column(name = "forwarder_code")
    @Size(max = 10)
    private String forwarderCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(Long id, String email, String password, String iinBin, String phone,
                String address, String companyName, String companyDirector, String firstName, String lastName,
                String middleName, boolean isCompany, LocalDate createdDate, LocalDate lastModifiedDate,
                String kato, String forwarderCode, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.iinBin = iinBin;
        this.phone = phone;
        this.address = address;
        this.companyName = companyName;
        this.companyDirector = companyDirector;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.isCompany = isCompany;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.kato = kato;
        this.forwarderCode = forwarderCode;
        this.roles = roles;
    }
}

