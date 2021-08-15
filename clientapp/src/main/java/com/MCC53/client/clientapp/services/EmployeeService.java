/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.client.clientapp.services;


import com.MCC53.client.clientapp.models.Employee;
import com.MCC53.client.clientapp.models.ResponseData;
import com.MCC53.client.clientapp.models.ResponseData;
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
 * @author user
 */
@Service
public class EmployeeService {
   
    @Autowired
    private RestTemplate restTemplate;
    
    @Value ("${api.baseUrl}/employee")
    private String baseUrl;
    
    //getAll
    public List<Employee> getAll (){
        
        ResponseEntity<List<Employee>> respon = restTemplate.exchange(baseUrl,
                HttpMethod.GET,null,new ParameterizedTypeReference<List<Employee>>() {});
        
        return respon.getBody();
    }
    
    public Employee getByid (Long id){
        ResponseEntity <Employee> respon = restTemplate
                .getForEntity(baseUrl +"/"+id,Employee.class);
        
        return respon.getBody();
        
    }
    //create 
    public Employee create (Employee employee){
        
        ResponseEntity<Employee> respon = restTemplate
                .postForEntity(baseUrl, employee,Employee.class);
        
        return respon.getBody();
    }
    
    public String delete (Long id){
        
        restTemplate.delete(baseUrl+"/"+id,Employee.class);
        
        return "delete sukses";
    }
    
    public String updateDepartment (Long id, Employee department){
        
        restTemplate.put(baseUrl+"/"+id,department,Employee.class);
        
        return "update sukses";
    }
}
