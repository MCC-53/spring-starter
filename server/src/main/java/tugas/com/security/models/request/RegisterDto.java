package tugas.com.security.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Long department_id;
    private String username;
    private String password;
}
