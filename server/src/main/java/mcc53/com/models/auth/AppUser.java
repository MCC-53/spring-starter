package mcc53.com.models.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mcc53.com.models.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_auth")
public class AppUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128,nullable = false)
    private String username;

    @Column(length = 256,nullable = false)
    private String password;

    @Column(nullable = true)
    @Value("true")
    private boolean is_account_non_expired;

    @Column(nullable = true)
    @Value("true")
    private boolean is_account_non_locked;

    @Column(nullable = true)
    @Value("true")
    private boolean is_credential_non_expired;

    @Column(nullable = true)
    @Value("true")
    private boolean is_enable;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Employee employee;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_auth_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @Override
    //Ini terkait dengan Authorization nya, ex:Super_Admin,Admin,User
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority>authority = new ArrayList<>();//Inisialiasikan List dengan type GrantedAuth untuk menyimpan rolesnya
        for(Role role:getRoles()){ //Kita cocokkan role dengan role yang di role didatabase AppUser
            authority.add(new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()));//Jika ada masukkan kedalam list authority
            for(Previlage previlage: role.getPrevilages()){
                authority.add(new SimpleGrantedAuthority(previlage.getName().toUpperCase()));
            }
        }
        return authority;
    }

    public AppUser(Long id, String username, String password, Employee employee) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.employee = employee;
    }

    @Override
    //Untuk Return password
    public String getPassword() {
        return password;
    }

    @Override
    //Untuk Return username
    public String getUsername() {
        return username;//Ini yang akan dijadikan username untuk Auth
    }

    @Override
    //Status akun, apakah dia masih digunakan atau tidak
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    //Status akunnya, sedang di kunci atau tidak (true/false)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //Apakah passwordnya bisa expired atau tidak
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //Apakah akunnya enable atau tidak
    public boolean isEnabled() {
        return true;
    }
}
