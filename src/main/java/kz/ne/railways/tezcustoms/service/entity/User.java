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
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "iin_bin"),
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
    @Column(name = "iin_bin")
    @Size(max = 12)
    private String iinBin;

    @NotBlank
    @Size(max = 50)
    private String phone;

    @Size(max = 250)
    private String address;

    @Column(name = "company_name")
    @Size(max = 120)
    private String companyName;

    @Column(name = "company_director")
    @Size(max = 120)
    private String companyDirector;

    @Column(name = "first_name")
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name")
    @Size(max = 50)
    private String lastName;

    @Column(name = "middle_name")
    @Size(max = 50)
    private String middleName;

    @Column(columnDefinition = "boolean default false", name = "is_company")
    private boolean isCompany;

    @Column(name = "created_date")
    @CreatedDate
    private LocalDate createdDate;

    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDate lastModifiedDate;

    @Size(max = 10)
    private String kato;

    @Column(name = "expeditor_code")
    @Size(max = 10)
    private String expeditorCode;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String email, String password, String iinBin, String phone,
                String address, String companyName, String companyDirector, String firstName, String lastName,
                String middleName, boolean isCompany, /*LocalDate createdDate, LocalDate lastModifiedDate,*/
                String kato, String expeditorCode, Set<Role> roles) {
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
//        this.createdDate = createdDate;
//        this.lastModifiedDate = lastModifiedDate;
        this.kato = kato;
        this.expeditorCode = expeditorCode;
        this.roles = roles;
    }
}

