/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import com.springboottest.demo.models.Departments;
import com.springboottest.demo.models.Employees;
import com.springboottest.demo.repositories.DepartmentsRepository;
import com.springboottest.demo.repositories.EmployeesRepository;
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
public class EmployeesDepartmentService {
    private EmployeesRepository employeesRepository;
    private DepartmentsRepository departmentsRepository;
    @Autowired
    public EmployeesDepartmentService(EmployeesRepository employeesRepository, DepartmentsRepository departmentsRepository) {
        this.employeesRepository = employeesRepository;
        this.departmentsRepository = departmentsRepository;
    } public Departments findByEmployeesId(Long id) {
        return departmentsRepository.findByEmployees_id(id);
    } public List<Employees> findByDepartmentsId(Long id) {
        departmentsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data department tidak ditemukan!"));
        return employeesRepository.findByDepartments_id(id);
    }
}