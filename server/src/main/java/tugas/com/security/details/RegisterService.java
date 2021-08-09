package tugas.com.security.details;

import tugas.com.security.models.request.RegisterDto;
import tugas.com.security.models.Employee;
import tugas.com.security.models.User;

public interface RegisterService {
    Employee saveEmployee(RegisterDto registerDto);
    User saveUser(RegisterDto registerDto);
}
