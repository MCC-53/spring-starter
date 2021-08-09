package tugas.com.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tugas.com.security.models.dto.RegisterDto;
import tugas.com.security.models.Department;
import tugas.com.security.models.Employee;
import tugas.com.security.models.User;
import tugas.com.security.repositories.EmployeeRepository;
import tugas.com.security.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user ==null) {
            throw new UsernameNotFoundException("User with username '%s' Not Fount");
        }
        return new UserDetail(user);

    }

//    public UserDetail registerAppUser(UserDetail user){
//        boolean userExist = userRepository.findByUsername(user.getUsername());
//    }
}
