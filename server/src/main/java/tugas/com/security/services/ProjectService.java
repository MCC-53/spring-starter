package tugas.com.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tugas.com.security.models.Project;
import tugas.com.security.repositories.EmployeeRepository;
import tugas.com.security.repositories.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    public List<Project> findByEmployeesId(Long employeeId){
        employeeRepository.findById(employeeId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));
        return projectRepository.findByEmployees_Id(employeeId);
    }

    public Project getById(Long id){
        return projectRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Id Not Found"));
    }

    public Project create(Project project){
        if (project.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Project Already Exist");
        }
        return projectRepository.save(project);
    }

    public Project update(Long id, Project project){
        getById(id);
        project.setId(id);
        return projectRepository.save(project);
    }

    public Project delete(Long id){
        Project project = getById(id);
        projectRepository.deleteById(id);
        return  project;
    }
}
