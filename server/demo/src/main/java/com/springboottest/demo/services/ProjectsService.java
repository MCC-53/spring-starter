/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.Projects;
import com.springboottest.demo.repositories.ProjectsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
/**
 *
 * @author ACER
 */
@Service
public class ProjectsService {
    private ProjectsRepository projectsRepository;
    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository) {
        this.projectsRepository = projectsRepository;
    } public List<Projects> getAll() {
        return projectsRepository.findAll();
    } public Projects getById(Long id) {
        return projectsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data project tidak ditemukan!"));
    } public Projects create(Projects projects) {
        if (projects.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data employee sudah ada!");
        } return projectsRepository.save(projects);
    } public Projects update(Long id, Projects projects) {
        projects.setId(getById(id).getId());
        return projectsRepository.save(projects);
    } public Projects delete(Long id) {
        Projects projects = getById(id);
        projectsRepository.deleteEmployeeProject(projects.getId());
        projectsRepository.deleteById(id);
        return projects;
    } public List<Projects> findByEmployeesId(Long id) {
        projectsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data department tidak ditemukan!"));
        return projectsRepository.findByEmployees_id(id);
    }
}