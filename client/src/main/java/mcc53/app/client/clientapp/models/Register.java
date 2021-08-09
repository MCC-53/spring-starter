package mcc53.app.client.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author firmanzega
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Register {
    
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String deptId;
    private String nameuser;
    private String password;
    
}
