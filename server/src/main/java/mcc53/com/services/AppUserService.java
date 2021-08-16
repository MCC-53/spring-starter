package mcc53.com.services;

import mcc53.com.models.Department;
import mcc53.com.models.Employee;
import mcc53.com.models.SendEmail;
import mcc53.com.models.auth.AppUser;
import mcc53.com.models.dto.RegisterDTO;
import mcc53.com.repositories.AppUserRepository;
import mcc53.com.repositories.EmployeeRepository;
import mcc53.com.security.BcryptEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class AppUserService implements UserDetailsService {
    private EmployeeRepository employeeRepository;
    private AppUserRepository appUserRepository;
    private BcryptEncode bcryptEncode;
    private SendEmailService sendEmailService;

    @Autowired
    public AppUserService(EmployeeRepository employeeRepository, AppUserRepository appUserRepository, BcryptEncode bcryptEncode, SendEmailService sendEmailService) {
        this.employeeRepository = employeeRepository;
        this.appUserRepository = appUserRepository;
        this.bcryptEncode = bcryptEncode;
        this.sendEmailService = sendEmailService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.
                format("username  '%s' tidak ada", username))); //%s akan di diisi dengan email
    }

    //Create SignUp
    public RegisterDTO registerAppUser(RegisterDTO registerDTO) {
        AppUser user = new AppUser();
        boolean usercheck = appUserRepository.findByUsername(user.getUsername()).isPresent();
        if (usercheck) {
            //Jika email sudah ada, kasih message error
            throw new RuntimeException(String.format("username '%s' sudah ada", user.getUsername()));
        } else {
            Employee employee = new Employee();
            employee.setFirstName(registerDTO.getFirstName());
            employee.setLastName(registerDTO.getLastName());
            employee.setAddress(registerDTO.getAddress());
            employee.setEmail(registerDTO.getEmail());
            employee.setDepartment(new Department(registerDTO.getDepartment_id()));
            //Lakukan Enkripsi
            String encodePass = bcryptEncode.bcryptEncoder().encode(registerDTO.getPassword());
            user.setUsername(registerDTO.getUsername());
            user.setPassword(encodePass);
            user.setEmployee(employeeRepository.save(employee));
            appUserRepository.save(user);

            SendEmail sendEmail = new SendEmail();
            sendEmail.setTo(registerDTO.getEmail());
            sendEmail.setSubject("Selamat Anda Terdaftar");
            sendEmailService.sendSimpleMessage(sendEmail, registerContext(registerDTO));
            return registerDTO;
        }
    }

    private Context registerContext(RegisterDTO registerDTO) {
        Context context = new Context();
        context.setVariable("fullName", registerDTO.getFirstName()+" "+registerDTO.getLastName());
        return context;
    }
}
