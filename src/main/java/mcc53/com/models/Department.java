/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author WahyuKu
 */
@Entity
@Table(name = "department")
@Data
@ToString
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    
    //Department -> List<Employee> -> saveAll
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Department(Long id, String name, 
            List<Employee> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Department() {
    }
}
