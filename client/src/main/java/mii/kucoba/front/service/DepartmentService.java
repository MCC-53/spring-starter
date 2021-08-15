/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.kucoba.front.service;

import java.nio.charset.Charset;
import java.util.List;
import mii.kucoba.front.models.Department;
import mii.kucoba.front.models.Project;
import mii.kucoba.front.models.request.ReturnMessage;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author abiyo
 */
@Service
public class DepartmentService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.backend}/department")
    private String baseUrl;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Department> getDepartmentData(){
        ResponseEntity<List<Department>> res = restTemplate.exchange(baseUrl, 
                HttpMethod.GET, new HttpEntity<>(createHeaders()) ,new ParameterizedTypeReference<List<Department>>(){});
        return res.getBody();
    }
    
    public Department createDataDepartment(Department d){
        ResponseEntity<Department> dep = restTemplate.postForEntity(baseUrl, d, Department.class);
        return dep.getBody();
    }
    
    public String deleteDataDepartment(Integer departmentId){
        restTemplate.delete(baseUrl +"/"+ departmentId, Project.class);
        return "done";
    }
    
    public Department getDepartmentById(Integer departmentId){
        ResponseEntity<Department> proj = restTemplate.getForEntity(baseUrl + "/" + departmentId, Department.class);
        return proj.getBody();
    }
    
    public String UpdateDepartment(Integer departmentId, Department d){
        restTemplate.put(baseUrl + "/" + departmentId, d, Project.class);
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
