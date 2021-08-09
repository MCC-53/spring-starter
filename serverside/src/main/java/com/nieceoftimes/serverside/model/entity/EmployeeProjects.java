package com.nieceoftimes.serverside.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "employee_projects")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProjects {
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
}
