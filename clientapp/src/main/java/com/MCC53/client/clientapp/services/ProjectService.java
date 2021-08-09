/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.client.clientapp.services;

import com.MCC53.client.clientapp.models.Project;
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
 * @author user
 */
@Service
public class ProjectService {
    
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value ("${api.baseUrl}/project")
    private String baseUrl;
//================================================================================
    //getAll
    public List<Project> getAll(){
      ResponseEntity<List<Project>> respon = 
              restTemplate.exchange(baseUrl,
                HttpMethod.GET,null,new ParameterizedTypeReference<List<Project>>() {});
        
        return respon.getBody();
    
    }
     public Project getByIdProject (Long id) {
        ResponseEntity <Project> respon = restTemplate
               .getForEntity(baseUrl+"/"+id, Project.class);
        
        return respon.getBody();
    }
//================================================================================     
    //create
    public Project createProject (Project project){
        ResponseEntity<Project> respon = restTemplate
                .postForEntity(baseUrl,project ,Project.class);
        
        return respon.getBody();
    }
    public String deleteProject (Long id){
        restTemplate.delete(baseUrl+"/"+id, Project.class);
        
        return "done";
    }
    public String updateProject (Long id, Project project){
        restTemplate.put(baseUrl+"/"+id, project, Project.class);
        return "done";
    }
}
