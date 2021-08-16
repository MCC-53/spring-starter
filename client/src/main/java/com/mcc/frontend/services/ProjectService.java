/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.frontend.services;

import com.mcc.frontend.models.Project;
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
public class ProjectService {
    private RestTemplate restTemplate;
    
    @Value("${api.serverURL}/project")
    private String apiUrl;

    @Autowired
    public ProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        
    }
    
    public List<Project> getAll(){
        ResponseEntity<List<Project>> res = restTemplate.exchange
                (apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Project>>() {});
        return res.getBody();
    }
    
    public Project create (Project project){
        ResponseEntity<Project> res = restTemplate.postForEntity(apiUrl, project, Project.class);
        return res.getBody();
    }
    
    public String delete(Integer id){
        restTemplate.delete(apiUrl +"/"+ id, Project.class);
        return "done";
    }
    
    public Project getById(Integer id){
        ResponseEntity<Project> res = restTemplate.getForEntity(apiUrl + "/" + id, Project.class);
        return res.getBody();
    }
    
    public String update(Integer id, Project project){
        restTemplate.put(apiUrl + "/" + id, project, Project.class);
        return "done";
    }
}
