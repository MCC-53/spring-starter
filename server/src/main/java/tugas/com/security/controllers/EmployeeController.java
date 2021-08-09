package tugas.com.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tugas.com.security.models.Employee;
import tugas.com.security.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/show")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/showbyid/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id){
        return new ResponseEntity(employeeService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_EMPLOYEE')")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        return new ResponseEntity(employeeService.create(employee), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE_EMPLOYEE')")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employee){
        return new ResponseEntity(employeeService.update(id,employee), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE_EMPLOYEE')")
    public ResponseEntity<Employee> delete(@PathVariable("id") Long id){
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }
}
