package tugas.com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import tugas.com.security.models.SendEmail;
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
    private SendEmailService sendEmailService;

    @Autowired
    public AuthService(EmployeeRepository employeeRepository, UserRepository userRepository,
                       PasswordEncoder passwordEncoder, SendEmailService sendEmailService) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sendEmailService = sendEmailService;
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

        SendEmail sendEmail = new SendEmail();
        sendEmail.setTo(registerDto.getEmail());
        sendEmail.setSubject("Selamat Anda Terdaftar");
        sendEmailService.sendSimpleMessage(sendEmail, registerContext(registerDto));

        return registerDto;
    }

    private Context registerContext(RegisterDto registerDto){
        Context context = new Context();
        context.setVariable("fullName", registerDto.getFirstName()+" "+registerDto.getLastName());
        return context;
    }

}
