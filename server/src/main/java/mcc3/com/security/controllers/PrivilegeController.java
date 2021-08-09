package mcc3.com.security.controllers;

import java.util.List;
import mcc3.com.security.models.entities.Privilege;
import mcc3.com.security.models.request.ResponseData;
import mcc3.com.security.services.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author firmanzega
 */
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    private PrivilegeService privilegeService;

    @Autowired
    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }
    
    @GetMapping
    public ResponseEntity<ResponseData<List<Privilege>>> getAll(){ //OK
        ResponseData<List<Privilege>> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessage("Get All Privileges");
        responseData.setPayLoad(privilegeService.getAll());
        return ResponseEntity.ok(responseData);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Privilege>> getById( //OK
            @PathVariable("id") Integer id) {
        ResponseData<Privilege> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessage("Get Project By Id " + id);
        responseData.setPayLoad(privilegeService.getById(id));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping
    public ResponseEntity<ResponseData<Privilege>> create( //OK
            @RequestBody Privilege privilege) {
        ResponseData<Privilege> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessage(privilege.getName() + " Department has been created");
        responseData.setPayLoad(privilegeService.create(privilege));
        return ResponseEntity.ok(responseData);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Privilege>> update( //OK
            @PathVariable("id") Integer id, @RequestBody Privilege privilege) {
        ResponseData<Privilege> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Department ID " + id + " has been updated");
        responseData.setPayLoad(privilegeService.update(id, privilege));
        return ResponseEntity.ok(responseData);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Privilege>> delete( //OK
            @PathVariable("id") Integer id) {
        ResponseData<Privilege> responseData = new ResponseData<>();
        responseData.setStatus(true);
        responseData.setMessage("Privilege ID " + id + " has been deleted");
        responseData.setPayLoad(privilegeService.delete(id));
        return ResponseEntity.ok(responseData);
    }
    
}
