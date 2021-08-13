package mcc53.com.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Privilege")
public class Privilege {

    @Id
    @Column(name = "privilege_id")
    private Long privilegeId;

    @Column(name = "privilege_name")
    private String privilegeName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;
}
