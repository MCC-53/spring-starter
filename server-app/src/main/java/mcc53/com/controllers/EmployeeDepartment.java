package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Department;
import mcc53.com.models.Employee;
import mcc53.com.services.DepartmentService;
import mcc53.com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee-department")
//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class EmployeeDepartment {
    
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    
    @Autowired
    public EmployeeDepartment(EmployeeService employeeService, 
            DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }
    
    @GetMapping("/department/{id}")
//    @PreAuthorize("hasAuthority('READ_EMPLOYEE_BY_DEPARTMENT_ID')")
    public ResponseEntity<List<Employee>> getByDepartmentId(
            @PathVariable("id") Long departmentId) {
        return new ResponseEntity(employeeService.findByDepartmentId(departmentId), HttpStatus.OK);
    }
    
    @GetMapping("/employee/{id}")
//    @PreAuthorize("hasAuthority('READ_DEPARTMENT_BY_EMPLOYEE_ID')")
    public ResponseEntity<Department> getByEmployeeId(@PathVariable("id") Long id) {
        return new ResponseEntity(departmentService.findByEmployeeId(id), HttpStatus.OK);
    }
}
