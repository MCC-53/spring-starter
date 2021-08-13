package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Employee;
import mcc53.com.models.ResponseMessage;
import mcc53.com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class EmployeeController {

    private EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
//    @PreAuthorize("hasAuthority('READ_EMPLOYEE')")
    public ResponseEntity<List<Employee>> getAlll() {
        return new ResponseEntity(employeeService.getAlll(), HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('READ_EMPLOYEE_BY_ID')")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(employeeService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE_EMPLOYEE')")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        return new ResponseEntity(new ResponseMessage<Employee>
            (employeeService.createEmployee(employee), "employee created"), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('UPDATE_EMPLOYEE')")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id,
            @RequestBody Employee employee) {
        return new ResponseEntity(employeeService.update(id, employee), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE_EMPLOYEE')")
    public ResponseEntity<Employee> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }
}
