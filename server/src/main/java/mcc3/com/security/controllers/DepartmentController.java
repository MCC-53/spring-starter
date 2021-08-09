package mcc3.com.security.controllers;

import java.util.List;
import mcc3.com.security.models.entities.Department;
import mcc3.com.security.models.request.ResponseData;
import mcc3.com.security.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author firmanzega
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<Department>>> getAll() { //OK
        ResponseData<List<Department>> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get All Departments");
        responseData.setPayLoad(departmentService.getAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Department>> getById( //OK
            @PathVariable("id") Long id) {
        ResponseData<Department> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessage("Get Department By Id " + id);
        responseData.setPayLoad(departmentService.getById(id));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Department>> create( //OK
            @RequestBody Department department) { 
        ResponseData<Department> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage(department.getName() + " Department has been created");
        responseData.setPayLoad(departmentService.create(department));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Department>> update(@PathVariable("id") Long id,
            @RequestBody Department department) { //OK
        ResponseData<Department> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Department ID " + id + " has been updated");
        responseData.setPayLoad(departmentService.update(id, department));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Department>> delete(@PathVariable("id") Long id) { //OK
        ResponseData<Department> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessage("Department ID " + id + " has been deleted");
        responseData.setPayLoad(departmentService.delete(id));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/employee/")
    public ResponseEntity<ResponseData<Department>> getByEmployeeId(//OK
            @RequestParam("id") Long employeeId) {
        ResponseData<Department> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessage("Get Department By Employee Id "+employeeId);
        responseData.setPayLoad(departmentService.findByEmployeeId(employeeId));
        return ResponseEntity.ok(responseData);
    }
}
