package tugas.com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tugas.com.security.models.Department;
import tugas.com.security.repositories.DepartmentRepository;
import tugas.com.security.repositories.EmployeeRepository;

import java.util.List;

@Service
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public Department findByEmployeeId(Long id){
        return departmentRepository.findByEmployees_id(id);
    }

    public Department getById(Long id){
        return departmentRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department Not Found"
                ));
    }

    public Department create(Department department){
        if (department.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Department Already Exist");
        }

        return departmentRepository.save(department);
    }

    public Department update(Long id, Department department){
        getById(id);
        department.setId(id);
        return departmentRepository.save(department);
    }

    public Department delete(Long id){
        Department department = getById(id);
        departmentRepository.deleteById(id);

        return department;
    }
}
