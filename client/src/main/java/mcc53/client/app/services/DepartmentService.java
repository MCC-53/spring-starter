/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;

import java.util.List;
import mcc53.client.app.models.Department;
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
public class DepartmentService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.Url}/department")
    private String Url;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Department> getAllDepartment() {
        ResponseEntity<List<Department>> res = restTemplate.exchange(Url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Department>>() {});
        return res.getBody();
    }
    public Department create(Department department) {
        ResponseEntity<Department> res = restTemplate.postForEntity(Url, department, Department.class);
        return res.getBody();
    }
    
    public Department getDepartmentById(Integer departmentId) {
        ResponseEntity<Department> res = restTemplate.getForEntity(Url + "/" + departmentId, Department.class);
        return res.getBody();
    }
    
    public String updateDataDepartment (Integer idDepartment, Department department) {
        restTemplate.put(Url + "/" + idDepartment, department, Department.class);
        return "done";
    }
    public String deleteDataDepartment (Integer departmentId) {
        restTemplate.delete(Url + "/" + departmentId, Department.class);
        return "done";
    }
}
