package com.mcc53jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    @Column(name = "first_name")
    @Basic(optional = false)
    private String firstName;

    @Column(name = "last_name")
    @Basic(optional = false)
    private String lastName;

    @Column(name = "email")
    @Basic(optional = false)
    private String email;

    @Column(name = "address")
    @Basic(optional = false)
    private String address;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private User user;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "employees")
    private List<Project> projects;
//    @ManyToMany
//    @JoinTable(
//            name = "employee_project",
//            joinColumns = @JoinColumn(name = "employee_id"),
//            inverseJoinColumns = @JoinColumn(name = "project_id")
//    )
//    private List<Project> projects;

}
