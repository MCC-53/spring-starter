/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.frontend.services;


import com.mcc.frontend.models.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author HP
 */
@Service
public class EmployeeService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.serverURL}/employee")
    private String apiUrl;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public Employee getById (Integer id){
        ResponseEntity<Employee> res = restTemplate
                .getForEntity(apiUrl + "/" + id, Employee.class);
        
        return res.getBody();
    }
    
    public List<Employee> getAll(){
        ResponseEntity<List<Employee>> res = restTemplate.exchange
                    (apiUrl, HttpMethod.GET, null, 
                        new ParameterizedTypeReference<List<Employee>>() {});
        
        return res.getBody();
    }
}
