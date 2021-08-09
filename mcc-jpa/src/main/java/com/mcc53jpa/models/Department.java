package com.mcc53jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employee;

    public Department(Long id) {
        this.id = id;
    }
}
