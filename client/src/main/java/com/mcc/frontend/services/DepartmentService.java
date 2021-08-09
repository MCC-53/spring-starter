/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.frontend.services;

import com.mcc.frontend.models.Department;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author HP
 */
@Service
public class DepartmentService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.serverURL}/department")
    private String apiUrl;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Department> getAll(){
        ResponseEntity<List<Department>> res = restTemplate.exchange
                (apiUrl, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<Department>>(){});
        
        return res.getBody();
    }
    
    public Department getById(Integer departmentId){
        ResponseEntity<Department> res = restTemplate
                .getForEntity(apiUrl + "/" + departmentId, Department.class);
        
        return res.getBody();
    }
    
    public Department create(Department department){
        ResponseEntity<Department> res = restTemplate.postForEntity(apiUrl, department, Department.class);
        return res.getBody();
    }
    
    public String delete(Integer id){
        restTemplate.delete(apiUrl + "/" + id, Department.class);
        return "done";
    }
    
    public String update(Integer id, Department department){
        restTemplate.put(apiUrl + "/" + id, department, Department.class);
        return "done";
    }
    
}
