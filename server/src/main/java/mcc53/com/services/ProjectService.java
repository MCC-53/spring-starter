/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.services;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mcc53.com.models.Employee;
import mcc53.com.models.Project;
import mcc53.com.repositories.EmployeeRepository;
import mcc53.com.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author WahyuKu
 */

@Service
public class ProjectService {

    @Autowired
    public ProjectService(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    private ProjectRepository projectRepository;
    private EmployeeRepository employeeRepository;

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public Project getById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Project not found"));
    }

    public Project create(Project project) {
        if (project.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Project already exist");
        }
        return projectRepository.save(project);
    }

    public Project update(Long id, Project project) {
        getById(id);
        project.setId(id);
        return projectRepository.save(project);
    }

    public Project delete(Long id) {
        Project project = getById(id);
        projectRepository.deleteById(id);
        return project;
    }

    public Project checkIdS(Long id){
        return projectRepository.checkExistId(id);
    }

    public List<Project> findByEmployeeId(Long id) {
        Optional<Employee> temp = employeeRepository.findById(id);
        if (!temp.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Employee id not found");
        } else {
            return projectRepository.findByEmployees_id(id);
        }
    }

}
