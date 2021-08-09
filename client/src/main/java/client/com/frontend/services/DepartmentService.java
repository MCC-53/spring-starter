/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.com.frontend.services;

import client.com.frontend.models.Department;
import client.com.frontend.models.ResponseMessage;
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
 * @author aceng
 */
@Service
public class DepartmentService {
    private RestTemplate restTemplate;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${api.baseUrl}/department")
    private String baseUrl;
    
    public List<Department> getAll(){
        ResponseEntity<List<Department>> response = restTemplate.exchange
                (baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference
                        <List<Department>>(){});
        return response.getBody();
    }
    
    public Department getById(Long id){
        ResponseEntity<Department> response = restTemplate
                .getForEntity(baseUrl+"/"+ id, Department.class);

        return response.getBody();
    }

    public Department create(Department department) {
        ResponseEntity<Department> res = restTemplate.postForEntity(baseUrl, department, Department.class);
        return res.getBody();
    }
    
    public String update(Department department, Long id){
        restTemplate.put(baseUrl+"/"+id, department, Department.class);
        return "done";
    }

    public String delete(Long id){
        restTemplate.delete(baseUrl+"/"+id, Department.class);
        return "success";
    }
}
