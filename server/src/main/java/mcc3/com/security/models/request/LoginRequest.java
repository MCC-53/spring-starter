package mcc3.com.security.models.request;

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
public class LoginRequest {
    
    private String username;
    private String password;
    
    
}
