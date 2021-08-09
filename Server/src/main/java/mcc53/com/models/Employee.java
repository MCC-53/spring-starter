/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Xvitas
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "address")
    private String address;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private User user;
    

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany()
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;
    
    
//    public Employee(Integer id, String firstName, String lastName, String email,
//            String address, Department department, List<Project> projects) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.address = address;
//        this.department = department;
//        this.projects = projects;
//    }
}
