package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Department;
import mcc53.com.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
public class DepartmentController {
    
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_DEPARTMENT')")
    public ResponseEntity<List<Department>> getAll() {
        return new ResponseEntity(departmentService.getAll(), HttpStatus.OK);
    }
    
        @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_DEPARTMENT_BY_NAME')")
    public ResponseEntity<List<Department>> getDepartmentById(@PathVariable("id") Long id) {
        return new ResponseEntity(departmentService.findDepartmentById(id), HttpStatus.OK);
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_DEPARTMENT')")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return new ResponseEntity(departmentService.createDepartement(department), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_DEPARTMENT')")
    public ResponseEntity<Department> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
        return new ResponseEntity(departmentService.updateDepartment(id, department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_DEPARTMENT')")
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Long id){
        return new ResponseEntity(departmentService.deleteDepartment(id), HttpStatus.OK);
    }
}
