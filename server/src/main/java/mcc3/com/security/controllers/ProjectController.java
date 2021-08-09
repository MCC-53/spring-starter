package mcc3.com.security.controllers;

import java.util.List;
import mcc3.com.security.models.entities.Project;
import mcc3.com.security.models.request.ResponseData;
import mcc3.com.security.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author firmanzega
 */

@RestController
@RequestMapping("/project")
public class ProjectController {
    
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @GetMapping
    public ResponseEntity<ResponseData<List<Project>>> getAll() { //OK
        ResponseData<List<Project>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessage("Get All Projects");
        responseData.setPayLoad(projectService.getAll());
        return ResponseEntity.ok(responseData);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Project>> getById( //OK
            @PathVariable("id") Long id) {
        ResponseData<Project> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get Project By Id " + id);
        responseData.setPayLoad(projectService.getById(id));
        return ResponseEntity.ok(responseData);
    }
    
    @PostMapping
    public ResponseEntity<ResponseData<Project>> create( //OK
            @RequestBody Project project) {
        ResponseData<Project> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage(project.getName() + " Project has been created");
        responseData.setPayLoad(projectService.create(project));
        return ResponseEntity.ok(responseData);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Project>> update( //OK
            @PathVariable("id") Long id, @RequestBody Project project) {
        ResponseData<Project> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Project ID " + id + " has been updated");
        responseData.setPayLoad(projectService.update(id, project));
        return ResponseEntity.ok(responseData);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Project>> delete( //OK
            @PathVariable("id") Long id) {
        ResponseData<Project> responseData = new ResponseData<>();
        System.out.println(id);
        responseData.setStatus(true);
        responseData.setMessage("Project ID " + id + " has been deleted");
        responseData.setPayLoad(projectService.delete(id));
        return ResponseEntity.ok(responseData);
    }
    
    @GetMapping("/employee/")
    public ResponseEntity<ResponseData<List<Project>>> getByEmployeeId( //OK
            @RequestParam("id") Long employeeId) {
        ResponseData<List<Project>> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get Project By Employee Id "+employeeId);
        responseData.setPayLoad(projectService.findByEmployeeId(employeeId));
        return ResponseEntity.ok(responseData);
    }
}
