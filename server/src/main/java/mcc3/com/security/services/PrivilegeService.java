package mcc3.com.security.services;

import java.util.ArrayList;
import java.util.List;
import mcc3.com.security.models.entities.Privilege;
import mcc3.com.security.models.entities.Role;
import mcc3.com.security.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author firmanzega
 */
@Service
public class PrivilegeService {

    private PrivilegeRepository privilegeRepository;

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public List<Privilege> getAll() { //OK
        return privilegeRepository.findAll();
    }

    public Privilege getById(Integer id) { //OK
        return privilegeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Privilege not found!"));
    }

    public Privilege create(Privilege privilege) { //OK
        if (privilege.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Privilege already exist!");
        }
        return privilegeRepository.save(privilege);
    }

    public Privilege update(Integer id, Privilege privilege) { //OK
        Privilege oldData = getById(id);
        privilege.setId(oldData.getId());
        
        boolean roleEmpty = oldData.getRoles().isEmpty();
        if(!roleEmpty){
            List<Role> privilegeId = new ArrayList<>();
            privilegeId.add(new Role(oldData.getRoles().get(0).getId()));
            privilege.setRoles(privilegeId);
        }
        
        return privilegeRepository.save(privilege);
    }
    
    public Privilege delete(Integer id) { //OK
        Privilege privilege = getById(id);
        privilegeRepository.deleteById(id);
        return privilege;
    }
}
