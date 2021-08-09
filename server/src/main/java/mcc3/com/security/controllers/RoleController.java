package mcc3.com.security.controllers;

import java.util.List;
import mcc3.com.security.models.entities.Role;
import mcc3.com.security.models.request.ResponseData;
import mcc3.com.security.services.RoleService;
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
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<Role>>> getAll() { //OK
        ResponseData<List<Role>> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get All Roles");
        responseData.setPayLoad(roleService.getAll());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Role>> getById( //OK
            @PathVariable("id") Integer id) {
        ResponseData<Role> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Get Role By Id " + id);
        responseData.setPayLoad(roleService.getById(id));
        return ResponseEntity.ok(responseData);
    }
    
    
    @PostMapping
    public ResponseEntity<ResponseData<Role>> create( //OK
            @RequestBody Role role) {
        ResponseData<Role> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Role " + role.getName() + " has been created");
        responseData.setPayLoad(roleService.create(role));
        return ResponseEntity.ok(responseData);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Role>> update( //OK
            @PathVariable("id") Integer id, @RequestBody Role role) {
        ResponseData<Role> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Role ID " + id + " has been updated");
        responseData.setPayLoad(roleService.update(id, role));
        return ResponseEntity.ok(responseData);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Role>> delete( //OK
            @PathVariable Integer id){
        ResponseData<Role> responseData = new ResponseData<>();
        
        responseData.setStatus(true);
        responseData.setMessage("Role ID " + id + " has been deleted");
        responseData.setPayLoad(roleService.delete(id));
        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/role-privilege/")
    public ResponseEntity<ResponseData<Role>> createRolePrivilege( 
            @RequestParam("roleId") Integer roleId, @RequestParam("privilegeId") Integer privilegeId) {
        ResponseData<Role> responseData = new ResponseData<>();

        responseData.setStatus(true);
        responseData.setMessage("Table role_privilege has been added");
        roleService.createRolePrivilege(roleId, privilegeId);
        return ResponseEntity.ok(responseData);
    }
}
