/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;

import java.util.List;
import mcc53.client.app.models.Department;
import mcc53.client.app.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author haikal
 */
@Service
public class EmployeeService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.Url}/emp")
    private String Url;
    
    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Employee> getAllDataEmployee() {
        ResponseEntity<List<Employee>> res = restTemplate.exchange(Url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Employee>>() {});
        return res.getBody();
    }
    
    public Employee create(Employee employee) {
        ResponseEntity<Employee> res = restTemplate
                .postForEntity(Url, employee, Employee.class);
        
        return res.getBody();
    }
    
    public Employee getEmployeeById(Integer employeeId) {
        ResponseEntity<Employee> res = restTemplate.getForEntity(Url + "/" + employeeId, Employee.class);
        return res.getBody();
    }
    
    public String updateDataEmployee(Integer idEmployee, Employee employee) {
        restTemplate.put(Url + "/" + idEmployee, employee, Employee.class);
        return "done";
    }
    
    public String deleteDataEmployee(Integer employeeId) {
        restTemplate.delete(Url + "/" + employeeId, Employee.class);
        return "done";
    }
    
}
