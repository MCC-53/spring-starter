/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.services;

import java.util.List;
import java.util.Optional;
import mcc53.com.models.Employee;
import mcc53.com.models.Project;
import mcc53.com.repositories.DepartmentRepository;
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
public class EmployeeService {

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
                           ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository ;

    public  List<Employee> getAlll() {
        return employeeRepository.findAll();
    }
    
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                               HttpStatus.NOT_FOUND, "Employee not found"));
    }
    
    public Employee create(Employee employee) {
        //ketika client memasukan id ke model/object maka kita anggap data sudah ada
        if (employee.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Employee already exist");
        }
        return employeeRepository.save(employee);
    }
    
    public Employee update(Long id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeRepository.save(employee);
    }
    
    public Employee delete(Long id) {
        Employee employee = getById(id);
        employeeRepository.deleteById(id);
        return employee;
    }

    public List<Employee> searchAddressCompleteAddress( ){
        return employeeRepository.searchCompleteAddress();
    }

    public List<String> validFormatEmail(){
        return employeeRepository.validatingEmailFormat();
    }

    public List<Employee> findByDepartmentId(Long departmentId) {
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> 
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
        return employeeRepository.findByDepartment_id(departmentId);
    }

    public List<Employee> findByProjectId(Long projectId){
        Optional<Project> temp = projectRepository.findById(projectId);
        if(!temp.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Project id not found");
        }
        else {
            return employeeRepository.findByProjects_id(projectId);
        }
    }


}
