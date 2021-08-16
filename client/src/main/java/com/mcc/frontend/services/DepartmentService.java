/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.frontend.services;

import com.mcc.frontend.models.Department;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    private HttpHeaders createHeaders(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();

        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}
