package mcc3.com.security.models.request;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author firmanzega
 */
@Data
@ToString
public class RegisterData {
    
    private String firstName;
    
    private String lastName;
    
    private String email;

    private String address;
    
    private Long deptId;
    
    private String nameuser;
    
    private String password;
    
}
