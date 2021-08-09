package mcc3.com.security.controllers;

import java.util.List;
import mcc3.com.security.models.entities.Employee;
import mcc3.com.security.models.request.EmployeeData;
import mcc3.com.security.models.request.ResponseData;
import mcc3.com.security.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author firmanzega
 */
@RestController
@RequestMapping("/employee")
//@PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
public class EmployeeController { //OK

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
//    @PreAuthorize("hasAuthority('READ')") //OK
    public ResponseEntity<ResponseData<List<Employee>>> getAll() {
        ResponseData<List<Employee>> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get All Employees");
        responseData.setPayLoad(employeeService.getAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}") //OK
    public ResponseEntity<ResponseData<Employee>> getById(@PathVariable("id") Long id) {
        ResponseData<Employee> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get Employee By Id " + id);
        responseData.setPayLoad(employeeService.getById(id));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE')") //OK
    public ResponseEntity<ResponseData<Employee>> create(
            @RequestBody EmployeeData employeeData) {
        System.out.println(employeeData);
        ResponseData<Employee> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setMessage("Employee " + employeeData.getFirstName() + " has been created");
        responseData.setPayLoad(employeeService.create(employeeData));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('UPDATE')") //ok
    public ResponseEntity<ResponseData<Employee>> update(@PathVariable("id") Long id,
            @RequestBody EmployeeData employeeData) {
        ResponseData<Employee> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setMessage("Employee ID " + id + " has been updated");
        responseData.setPayLoad(employeeService.update(id, employeeData));
        return ResponseEntity.ok(responseData);
    }

//    @PreAuthorize("hasAuthority('DELETE')") //OK
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Employee>> delete(@PathVariable("id") Long id) {
        ResponseData<Employee> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Employee ID " + id + " has been deleted");
        responseData.setPayLoad(employeeService.delete(id));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/department/") //OK
    public ResponseEntity<ResponseData<List<Employee>>> getByDepartmentId(
            @RequestParam("id") Long departmentId) {
        ResponseData<List<Employee>> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get Employee By Department ID " + departmentId);
        responseData.setPayLoad(employeeService.findByDepartmentId(departmentId));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/project/") //OK
    public ResponseEntity<ResponseData<List<Employee>>> getByProjectId(@RequestParam("id") Long projectId) {
        ResponseData<List<Employee>> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get Employee By Project ID " + projectId);
        responseData.setPayLoad(employeeService.findByProjectId(projectId));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/filter")//OK
    public ResponseEntity<ResponseData<List<Employee>>> getEmployeeByLastName(
            @RequestParam("lastName") String lastName) {
        ResponseData<List<Employee>> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get Employee By Last Name " + lastName);
        responseData.setPayLoad(employeeService.getByLastName(lastName));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/employee-project/")//OK
    public ResponseEntity<ResponseData<Employee>> createEmployeeProject( 
            @RequestParam("employeeId") Long employeeId, @RequestParam("projectId") Long projectId) {
        ResponseData<Employee> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setMessage("Table employee_project has been added");
        employeeService.createEmployeeProject(employeeId, projectId);
        return ResponseEntity.ok(responseData);
    }

}
