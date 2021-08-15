package tugas.com.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tugas.com.security.models.Department;
import tugas.com.security.models.ResponseMessage;
import tugas.com.security.services.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_DATA')")
    public ResponseEntity<List<Department>> getAll(){
        return new ResponseEntity(departmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public ResponseEntity<Department> getById(@PathVariable("id") Long id){
        return new ResponseEntity(departmentService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public ResponseEntity<Department> create(@RequestBody Department department){
        return new ResponseEntity(departmentService.create(department), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public ResponseEntity<Department> update(@PathVariable("id") Long id, @RequestBody Department department){
        return new ResponseEntity(departmentService.update(id,department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public ResponseEntity<Department> delete(@PathVariable("id") Long id){
        return new ResponseEntity(departmentService.delete(id), HttpStatus.OK);
    }
}
