package mcc53.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
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
    @Basic(optional = true)
    private String address;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
    @PrimaryKeyJoinColumn
    private User user;
    
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
    
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

}
