package mcc3.com.security.services;

import mcc3.com.security.models.entities.Department;
import mcc3.com.security.models.entities.Employee;
import mcc3.com.security.models.entities.User;
import mcc3.com.security.models.request.RegisterData;
import mcc3.com.security.repositories.EmployeeRepository;
import mcc3.com.security.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author firmanzega
 */
@Service
public class RegisterService {

    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;
    private PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterData register(RegisterData data) {//OK
        Employee employee = new Employee();
        employee.setFirstName(data.getFirstName());
        employee.setLastName(data.getLastName());
        employee.setAddress(data.getAddress());
        employee.setEmail(data.getEmail());
        employee.setDepartment(new Department(data.getDeptId()));

        User user = new User();
        user.setUsername(data.getUsername());
        String encodedPassword = passwordEncoder.encode(data.getPassword());
        user.setPassword(encodedPassword);
        user.setEmployee(employeeRepository.save(employee));
        userRepository.save(user);

        return data;
    }
}
