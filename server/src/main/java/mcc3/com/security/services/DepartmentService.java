package mcc3.com.security.services;

import java.util.List;
import mcc3.com.security.models.entities.Department;
import mcc3.com.security.models.entities.Employee;
import mcc3.com.security.models.request.ResponseData;
import mcc3.com.security.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author firmanzega
 */
@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private EmployeeService employeeService;
    
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }
    

    public List<Department> getAll() { //OK
        return departmentRepository.findAll();
    }


    public Department getById(Long id) { //OK
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Department not found!"));
    }

    public Department create(Department department) { //OK
        if (department.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Department already exist!");
        }
        return departmentRepository.save(department);
    }

    public Department update(Long id, Department department) { //OK
        getById(id);
        department.setId(id);
        return departmentRepository.save(department);
    }

    public Department delete(Long id) { //OK
        Department department = getById(id);
        boolean isNotEmpty = !employeeService.findByDepartmentId(id).isEmpty();
        if(isNotEmpty){
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, 
                    "The column ID from Department matches in both Employee and Department");
        }
        departmentRepository.deleteById(id);
        return department;
    }

    public Department findByEmployeeId(Long id) { //OK
        return departmentRepository.findByEmployees_id(id);
    }

}
