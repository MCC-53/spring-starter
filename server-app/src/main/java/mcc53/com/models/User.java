package mcc53.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_account_non_expired")
    private Boolean is_account_non_expired;

    @Column(name = "is_account_non_locked")
    private Boolean is_account_non_locked;

    @Column(name = "is_credential_non_expired")
    private Boolean is_credential_non_expired;

    @Column(name = "is_enable")
    private Boolean is_enable;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Employee employee;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinTable(name = "UserRole", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
