package mcc53.com.models.dto;

import lombok.*;
import mcc53.com.models.Department;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    @NotEmpty(message = "firstName required")
    private String firstName;
    @NotEmpty(message = "lastName required")
    private String lastName;
    @NotEmpty(message = "email required")
    private String email;
    private String address;
    @NotEmpty(message = "username required")
    private String username;
    @NotEmpty(message = "password required")
    private String password;
    private Long department_id;
}
