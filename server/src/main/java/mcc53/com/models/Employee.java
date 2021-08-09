/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mcc53.com.models.auth.AppUser;


/**
 *
 * @author WahyuKu
 */
@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Employee First_Name required")
    @Column(name = "first_name")
    @Basic(optional = false)
    private String firstName;

    @NotEmpty(message = "Employee Last_Name required")
    @Column(name = "last_name")
    @Basic(optional = false)
    private String lastName;

    @NotEmpty(message = "Email Required")
    @Email(message = "Enter a valid email")
    @Column(name = "email", unique = true)
    @Basic(optional = false)
    private String email;
    
    @Column(name = "address")
    @Basic(optional = true)
    private String address;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private AppUser appUser;
}
