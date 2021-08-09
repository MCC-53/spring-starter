package tugas.com.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tugas.com.security.models.Department;
import tugas.com.security.models.Employee;
import tugas.com.security.services.DepartmentService;
import tugas.com.security.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee-department")
public class EmployeeDepartment {

    private EmployeeService employeeService;
    private DepartmentService departmentService;

    @Autowired
    public EmployeeDepartment(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Employee>> getByDepartmentId(
            @PathVariable("id") Long departmentId) {
        return new ResponseEntity(employeeService.findByDepartmentId(departmentId), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Department> getByEmployeeId(@PathVariable("id") Long id) {
        return new ResponseEntity(departmentService.findByEmployeeId(id), HttpStatus.OK);
    }

}
