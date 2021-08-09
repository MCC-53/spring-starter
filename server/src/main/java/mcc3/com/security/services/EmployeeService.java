package mcc3.com.security.services;

import java.util.ArrayList;
import java.util.List;
import mcc3.com.security.models.entities.Department;
import mcc3.com.security.models.entities.Employee;
import mcc3.com.security.models.entities.Project;
import mcc3.com.security.models.request.EmployeeData;
import mcc3.com.security.repositories.DepartmentRepository;
import mcc3.com.security.repositories.EmployeeRepository;
import mcc3.com.security.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author firmanzega
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public EmployeeService(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            ProjectRepository projectRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    public List<Employee> getAll() {//OK
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {//OK
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Employee not found!"));
    }

    public Employee create(EmployeeData employeeData) {//OK
//        if (role.getId() != null) {
//            throw new ResponseStatusException(
//                    HttpStatus.CONFLICT, "Role already exist!");
//        }
        Employee employee = new Employee();
        employee.setFirstName(employeeData.getFirstName());
        employee.setLastName(employeeData.getLastName());
        employee.setEmail(employeeData.getEmail());
        employee.setAddress(employeeData.getAddress());
        employee.setDepartment(new Department(employeeData.getDepartment().getId()));

        return employeeRepository.save(employee);
    }

    public Employee update(Long id, EmployeeData employeeData) { //OK
        Employee oldData = getById(id);
        Employee employee = new Employee();

        employee.setId(oldData.getId());
        employee.setFirstName(employeeData.getFirstName());
        employee.setLastName(employeeData.getLastName());
        employee.setEmail(employeeData.getEmail());
        employee.setAddress(employeeData.getAddress());
//        employee.setDepartment(new Department(employeeData.getDeptId()));
        employee.setDepartment(new Department(employeeData.getDepartment().getId()));

        boolean projectEmpty = oldData.getProjects().isEmpty();
        if (!projectEmpty) {
            List<Project> projectId = new ArrayList<>();
            projectId.add(new Project(oldData.getProjects().get(0).getId()));
            employee.setProjects(projectId);
        }

        return employeeRepository.save(employee);
    }

    public Employee delete(Long id) { //OK
        Employee employee = getById(id);
        employeeRepository.deleteById(id);
        return employee;
    }

    public List<Employee> findByDepartmentId(Long departmentId) { //OK
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Department not found!"));
        return employeeRepository.findByDepartment_id(departmentId);
    }

    public List<Employee> findByProjectId(Long projectId) { //OK
        projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Project not found!"));
        return employeeRepository.findByProjects_id(projectId);
    }

    public List<Employee> getByLastName(String lastName) { //OK
        return employeeRepository.findByLastName(lastName);
    }

    public void createEmployeeProject(Long employeeId, Long projectId) {
        projectRepository.findById(projectId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project not found!"));
        employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not found!"));

        employeeRepository.createEmployeeProject(employeeId, projectId);
    }
}
