package com.mcc53jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "projects_employees",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @ManyToMany(mappedBy = "projects")
//    private List<Employee> employees;

}
