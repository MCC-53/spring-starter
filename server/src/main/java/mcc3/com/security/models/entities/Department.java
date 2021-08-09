package mcc3.com.security.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author firmanzega
 */
@Entity
@Table(name = "department")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 30, nullable = false)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Department(Long id){
        this.id = id;
    }

}
