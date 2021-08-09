package tugas.com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tugas.com.security.models.request.RegisterDto;
import tugas.com.security.models.Department;
import tugas.com.security.models.Employee;
import tugas.com.security.models.User;
import tugas.com.security.repositories.EmployeeRepository;
import tugas.com.security.repositories.UserRepository;


@Service
public class AuthService {
    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(EmployeeRepository employeeRepository, UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterDto saveRegister(RegisterDto registerDto) {
//        System.out.println(registerDto.toString());
        Employee employee = new Employee();
        employee.setFirstName(registerDto.getFirstName());
        employee.setLastName(registerDto.getLastName());
        employee.setAddress(registerDto.getAddress());
        employee.setEmail(registerDto.getEmail());
        employee.setDepartment(new Department(registerDto.getDepartment_id()));

        User user = new User();
        user.setUsername(registerDto.getUsername());
//        user.setPassword(registerDto.getPassword());
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEmployee(employeeRepository.save(employee));
        userRepository.save(user);

        return registerDto;
    }

}
