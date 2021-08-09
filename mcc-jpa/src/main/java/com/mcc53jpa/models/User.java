package com.mcc53jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @Basic(optional = false)
    private String username;

    @Column(name = "password")
    @Basic(optional = false)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "is_account_non_expired")
    private Boolean isAccountNonExpired;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "is_account_non_locked")
    private Boolean isAccountNonLocked;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "is_credential_non_expired")
    private Boolean isCredentialNonExpired;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private Employee employee;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

}
