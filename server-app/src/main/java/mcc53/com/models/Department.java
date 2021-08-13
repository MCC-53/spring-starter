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

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
@Data
@ToString
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;

    @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Department(Long id) {
        this.id = id;
    }
    
}
