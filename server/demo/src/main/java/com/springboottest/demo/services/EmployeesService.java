/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springboottest.demo.services;
import java.util.List;
import com.springboottest.demo.models.Employees;
import com.springboottest.demo.repositories.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.springboottest.demo.repositories.EmployeesRepository;
/**
 *
 * @author ACER
 */
@Service
public class EmployeesService {
    private EmployeesRepository employeesRepository;
    private DepartmentsRepository departmentsRepository;
    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository, DepartmentsRepository departmentsRepository) {
        this.employeesRepository = employeesRepository;
        this.departmentsRepository = departmentsRepository;
    } public List<Employees> getAll() {
        return employeesRepository.findAll();
    } public Employees getById(Long id) {
        return employeesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data employee tidak ditemukan!"));
    } public Employees getByEmail(String email) {
        return employeesRepository.findByEmail(email);
    } public Employees getByEmployeesFirstName(String firstName) {
        return employeesRepository.findByEmployeesFirstName("%" + firstName + "%");
    } public Employees create(Employees employees) {
        if (employees.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data employee sudah ada!");
        } return employeesRepository.save(employees);
    } public Employees update(Long id, Employees employees) {
        getById(id);
        employees.setId(id);
        return employeesRepository.save(employees);
    } public Employees delete(Long id) {
        Employees employees = getById(id);
        employeesRepository.deleteById(id);
        return employees;
    } 
}