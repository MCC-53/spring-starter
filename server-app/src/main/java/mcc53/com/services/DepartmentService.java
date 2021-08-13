package mcc53.com.services;

import java.util.List;
import mcc53.com.models.Department;
import mcc53.com.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }
    
    public Department findByEmployeeId(Long id) {
        return departmentRepository.findByEmployees_id(id);
    }
    
    public Department createDepartement(Department department) {
        if (department.getId() !=null ){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Department Already Exist"
            );
        }
            
        return departmentRepository.save(department);
    }
    
    public Department findDepartmentById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department not found"
                ));
    }
    
    public Department updateDepartment(Long id, Department department){
        findDepartmentById(id);

        department.setId(id);

        return departmentRepository.save(department);
    }

    public Department deleteDepartment(Long id){
        Department department = findDepartmentById(id);

        departmentRepository.deleteById(id);
        return department;
    }
    
    public List<Department> findByDepartmentName(String name){
        return departmentRepository.findByDepartmentName(name);
    }
}
