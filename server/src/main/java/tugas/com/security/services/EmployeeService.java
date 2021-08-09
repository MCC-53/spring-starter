package tugas.com.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tugas.com.security.models.Employee;
import tugas.com.security.repositories.DepartmentRepository;
import tugas.com.security.repositories.EmployeeRepository;
import tugas.com.security.repositories.ProjectRepository;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee getById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Employee Not Found"
                ));
    }

    public Employee create(Employee employee){
        if (employee.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee Already Exist");
        }
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee employee){
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    public Employee delete(Long id){
        Employee employee = getById(id);
        employeeRepository.deleteById(id);
        employee.setProjects(null);
        return employee;
    }

    public List<Employee> findByDepartmentId(Long departmentId){
        departmentRepository.findById(departmentId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Department Not Found"));
        return employeeRepository.findByDepartment_id(departmentId);
    }

    public List<Employee> findByProjectsId(Long projectId){
        projectRepository.findById(projectId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Not Found"));
        return employeeRepository.findByProjects_id(projectId);
    }

    public Employee findByFirstName(String firstName){
        return employeeRepository.findByFirstName(firstName);
    }

    public List<Employee> findByEmailLike(String email){
        return employeeRepository.findByEmailLike("%"+email+"%");
    }

    public List<Employee> findEmployeeByDepartment(String department){
        return employeeRepository.findEmployeeByDepartment("%"+department+"%");
    }

}
