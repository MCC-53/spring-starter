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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author user
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table( name = "role")
public class Role {
    
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    @Column (name = "id", nullable = false)
    private Long id;
    
    @Column (name = "name", nullable = false)
    private String name;
    
    @ManyToMany (fetch =  FetchType.EAGER)
    @JoinTable (
            name = "Role_privilege",
            joinColumns = @JoinColumn (name = "role_id"),
            inverseJoinColumns = @JoinColumn (name = "privilege_id")
    )
           
    private List <Privilege> privileges;
    
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}

