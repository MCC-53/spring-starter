/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.springsecurity.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import static jdk.nashorn.internal.runtime.Debug.id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author user
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table( name = "user")
public class User {
    
    @Id
//    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Long id;
    
    
    @Column (name = "username", nullable = false)
    private String username;
    
    @Column (name = "password", nullable = false)
    private String password;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @MapsId
    @JoinColumn( name = "id")
    private Employee employee;

   
    
}
