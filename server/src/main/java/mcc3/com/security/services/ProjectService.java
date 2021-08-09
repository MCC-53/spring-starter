package mcc3.com.security.services;

import java.util.ArrayList;
import java.util.List;
import mcc3.com.security.models.entities.Employee;
import mcc3.com.security.models.entities.Project;
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
public class ProjectService {

    private ProjectRepository projectRepository;
    private EmployeeService employeeService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, EmployeeService employeeService) {
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
    }

    public List<Project> getAll() { //OK
        return projectRepository.findAll();
    }

    public Project getById(Long id) { //OK
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Project not found!"));
    }

    public Project create(Project project) { //OK
        return projectRepository.save(project);
    }

    public Project update(Long id, Project project) { //OK
        Project oldData = getById(id);
        project.setId(oldData.getId());
        boolean employeeEmpty = oldData.getEmployees().isEmpty();
        if (!employeeEmpty) {
            List<Employee> employeeId = new ArrayList<>();
            employeeId.add(new Employee(oldData.getEmployees().get(0).getId()));
            project.setEmployees(employeeId);
        }

        return projectRepository.save(project);
    }

    public Project delete(Long id) { //OK
        Project project = getById(id);
        
        projectRepository.deleteById(id);
        return project;
    }

    public List<Project> findByEmployeeId(Long employeeId) { //OK
        return projectRepository.findByEmployees_id(employeeId);
    }

}
