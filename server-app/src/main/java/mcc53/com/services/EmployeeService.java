package mcc53.com.services;

import java.util.List;
import java.util.Optional;
import mcc53.com.models.Employee;
import mcc53.com.repositories.DepartmentRepository;
import mcc53.com.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService {
    
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, 
            DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }
    
    public List<Employee> getAlll() {
        return employeeRepository.findAll();
    }
    
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                               HttpStatus.NOT_FOUND, "Employee not found"));
    }
    
    public Employee createEmployee(Employee employee) {
        //ketika client memasukan id ke model/object maka kita anggap data sudah ada
        System.out.println(employee.getDepartment().toString());
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
    public List<Employee> findByDepartmentId(Long departmentId) {
        departmentRepository.findById(departmentId)
                .orElseThrow(() -> 
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
        
        return employeeRepository.findByDepartment_id(departmentId);
    }
}
