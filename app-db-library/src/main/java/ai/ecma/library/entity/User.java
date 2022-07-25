package ai.ecma.library.entity;

import ai.ecma.library.enums.Language;
import ai.ecma.library.entity.templete.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Created by Kholmakhmatov on 14/06/2022 !
 **/
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Where(clause = "deleted = false")
@SQLDelete(sql = "update users set deleted = true where id = ?")
@Entity(name = "users")
public class User extends AbsEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Language language;

    @Column(unique = true, name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "extra_phone_number")
    private String extraPhoneNumber;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private boolean online;

    @Column(nullable = false)
    private String password="1111User";

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Attachment photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Address> addresses;

    @ManyToOne
    private District district;


    public User(String phoneNumber, String firstName, String password, boolean enabled, Role role, Language language) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
        this.language = language;
    }
}
