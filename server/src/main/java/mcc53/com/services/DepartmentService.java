/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.services;

import java.util.List;
import java.util.Optional;

import mcc53.com.models.Department;
import mcc53.com.models.Employee;
import mcc53.com.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author WahyuKu
 */
@Service
public class DepartmentService {
    
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department getById(Long id){
        //Return optional, find Id => temp
        Optional<Department> temp = departmentRepository.findById(id);
        //Id = null => error message
        if (!temp.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Department id not found");
        }
        else {
            return temp.get();
        }
    }

    public Department create(Department department){
        System.out.println(department.getName().toString());
        if (department.getId()!=null){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Department already exist");
        }
        else{
            return departmentRepository.save(department);
        }
    }

    public Department update(Long id,Department department){
        getById(id);
        department.setId(id);
        return departmentRepository.save(department);
    }

    public Department delete(Long id){
        Department department = getById(id);
        departmentRepository.deleteById(id);
        return department;
    }

    public List<Department> getDepartmentLikeName(String name){
        return departmentRepository.getNameLike(name);
    }

    public Long countSameName(String name){
        getDepartmentLikeName(name);
        return departmentRepository.countByLikeName(name);
    }

    public Department findByEmployeeId(Long id) {
        return departmentRepository.findByEmployees_id(id);
    }


}
