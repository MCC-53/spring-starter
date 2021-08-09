package tugas.com.security.controllers;

import org.springframework.web.bind.annotation.*;
import tugas.com.security.models.Employee;
import tugas.com.security.repositories.EmployeeRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private EmployeeRepository _employeeRepository;
    
    @Autowired
    public UserController(EmployeeRepository employeeRepository){
        _employeeRepository = employeeRepository;
    }
    
    @GetMapping
    public List<Employee> findAll(){
        return _employeeRepository.findAll();
    }

}
