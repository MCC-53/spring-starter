package tugas.com.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tugas.com.security.models.Employee;
import tugas.com.security.models.ResponseMessage;
import tugas.com.security.models.request.SearchData;
import tugas.com.security.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_DATA')")
    public ResponseEntity<List<Employee>> getAll(){
        return new ResponseEntity(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id){
        return new ResponseEntity(employeeService.getById(id), HttpStatus.OK);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE_EMPLOYEE')")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        return new ResponseEntity(new ResponseMessage<Employee>
                (employeeService.create(employee), "employee created"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('UPDATE_EMPLOYEE')")
    public ResponseEntity<Employee> update(@PathVariable("id") Long id, @RequestBody Employee employee){
        return new ResponseEntity(employeeService.update(id,employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('DELETE_EMPLOYEE')")
    public ResponseEntity<Employee> delete(@PathVariable("id") Long id){
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/search/firstName")
    public Employee showByName(@RequestBody SearchData searchData){
        return employeeService.findByFirstName(searchData.getSearchKey());
    }

    @PostMapping("/search/emaillike")
    public List<Employee> showByNameLike(@RequestBody SearchData searchData){
        return employeeService.findByEmailLike(searchData.getSearchKey());
    }

    @PostMapping("/search/departmentname")
    public List<Employee> showByDepartmentName(@RequestBody SearchData searchData){
        return employeeService.findEmployeeByDepartment(searchData.getSearchKey());
    }
}
