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
@Data
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

    @Column(name = "chat_id")
    private Long chatId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String email, String password, String iinBin, boolean isCompany, String firstName, String lastName,
                    String middleName, String companyName, String companyDirector, String address, String phone) {
        this.email = email;
        this.password = password;
        this.iinBin = iinBin;
        this.isCompany = isCompany;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.companyName = companyName;
        this.companyDirector = companyDirector;
        this.address = address;
        this.phone = phone;
    }
}

