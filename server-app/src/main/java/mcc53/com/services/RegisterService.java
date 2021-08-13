package mcc53.com.services;

import mcc53.com.models.*;
import mcc53.com.repositories.EmployeeRepository;
import mcc53.com.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class RegisterService {
    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;
    private SendEmailService sendEmailService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterService(EmployeeRepository employeeRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           SendEmailService sendEmailService) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sendEmailService = sendEmailService;
    }

    public RegisterRequest saveRegister(RegisterRequest registerRequest){
        Employee employee = new Employee();
        employee.setFirstName(registerRequest.getFirstName());
        employee.setLastName(registerRequest.getLastName());
        employee.setEmail(registerRequest.getEmail());
        employee.setAddress(registerRequest.getAddress());
        employee.setDepartment(new Department(registerRequest.getDepartment()));

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setEmployee(employeeRepository.save(employee));
        userRepository.save(user);

        SendEmail sendEmail = new SendEmail();
        sendEmail.setTo(registerRequest.getEmail());
        sendEmail.setSubject("Selamat anda terdaftar!");
        sendEmailService.sendSimpleMessage(sendEmail, registerContext(registerRequest));

        return registerRequest;
    }

    private Context registerContext(RegisterRequest registerRequest) {
        Context context = new Context();
        context.setVariable("fullName", registerRequest.getFirstName()+" "+registerRequest.getLastName());
        return context;
    }

}
