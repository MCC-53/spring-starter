package mcc3.com.security.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mcc3.com.security.models.entities.Department;
import mcc3.com.security.models.entities.User;

/**
 *
 * @author firmanzega
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeData { //OK
// Request Data untuk melakukan Insert dan update data employee
    private String firstName;
    
    private String lastName;
    
    private String email;

    private String address;
    
    private Department department;
    
    private User user;
    
}
