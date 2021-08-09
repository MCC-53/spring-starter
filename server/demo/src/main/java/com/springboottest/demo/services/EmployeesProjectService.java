/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.Projects;
import com.springboottest.demo.models.Employees;
import com.springboottest.demo.repositories.EmployeesRepository;
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
public class EmployeesProjectService {
    private EmployeesRepository employeesRepository;
    private ProjectsRepository projectsRepository;
    @Autowired
    public EmployeesProjectService(EmployeesRepository employeesRepository, ProjectsRepository projectsRepository) {
        this.employeesRepository = employeesRepository;
        this.projectsRepository = projectsRepository;
    } public List<Projects> findByEmployeesId(Long id) {
        return projectsRepository.findByEmployees_id(id);
    } public List<Employees> findByProjectsId(Long id) {
        projectsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data department tidak ditemukan!"));
        return employeesRepository.findByProjects_id(id);
    }
}