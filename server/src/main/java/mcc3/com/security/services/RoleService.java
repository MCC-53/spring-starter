package mcc3.com.security.services;

import java.util.ArrayList;
import java.util.List;
import mcc3.com.security.models.entities.Privilege;
import mcc3.com.security.models.entities.Role;
import mcc3.com.security.models.entities.User;
import mcc3.com.security.repositories.PrivilegeRepository;
import mcc3.com.security.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author firmanzega
 */

@Service
public class RoleService {
        
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }
    
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
    
    public Role getById(Integer id){
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Role not found"));
    }
    
    public Role create(Role role) {
        if (role.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Role already exist!");
        }
        return roleRepository.save(role);
    }
    
    public Role update(Integer id, Role role){
        Role oldData = getById(id);
        role.setId(oldData.getId());
        boolean privilegeEmpty = oldData.getPrivileges().isEmpty();
        if(!privilegeEmpty){
            List<Privilege> roleId = new ArrayList<>();
            roleId.add(new Privilege(oldData.getPrivileges().get(0).getId()));
            role.setPrivileges(roleId);
        }
        
        return roleRepository.save(role);
    }
    
    public Role delete(Integer id){
        Role role = getById(id);
        roleRepository.deleteById(id);
        return role;
    }
    
    public void createRolePrivilege(Integer roleId, Integer privilegeId){
        privilegeRepository.findById(privilegeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Privilege not found!"));
        roleRepository.findById(roleId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found!"));
        
        roleRepository.createRolePrivilege(roleId, privilegeId);
    }
    
}
