package com.nieceoftimes.serverside.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
