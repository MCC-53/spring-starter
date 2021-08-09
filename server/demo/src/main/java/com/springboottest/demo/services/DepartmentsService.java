/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.Departments;
import com.springboottest.demo.models.EmployeeDepartment;
import com.springboottest.demo.models.Employees;
import com.springboottest.demo.repositories.DepartmentsRepository;
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
public class DepartmentsService {
    private DepartmentsRepository departmentsRepository;
    private EmployeesRepository employeesRepository;
    private ProjectsRepository projectsRepository;
    @Autowired
    public DepartmentsService(DepartmentsRepository departmentsRepository, EmployeesRepository employeesRepository, ProjectsRepository projectsRepository) {
        this.departmentsRepository = departmentsRepository;
        this.employeesRepository = employeesRepository;
        this.projectsRepository = projectsRepository;
    } public List<Departments> getAll() {
        return departmentsRepository.findAll();
    } public Departments getById(Long id) {
        return departmentsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data department tidak ditemukan!"));
    } public Departments create(Departments departments) {
        if (departments.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data employee sudah ada!");
        } return departmentsRepository.save(departments);
    } public Departments update(Long id, Departments departments) {
        departments.setId(getById(id).getId());
        return departmentsRepository.save(departments);
    } public Departments delete(Long id) {
        Departments departments = getById(id);
        departmentsRepository.deleteById(id);
        return departments;
    } public EmployeeDepartment createEmployeeDepartment(EmployeeDepartment employeeDepartment) {
        if (employeeDepartment.getDepartments().getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data department sudah ada!");
        } employeeDepartment.setDepartments(departmentsRepository.save(employeeDepartment.getDepartments()));
        employeesRepository.saveAll(employeeDepartment.getEmployees());
        return employeeDepartment;
    }
}