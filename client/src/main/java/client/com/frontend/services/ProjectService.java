/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.com.frontend.services;

import client.com.frontend.models.Project;
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
public class ProjectService {
    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/project")
    private String baseUrl;

    @Autowired
    public ProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Project> getAllProject(){
        ResponseEntity<List<Project>> res = restTemplate.exchange(baseUrl, 
                HttpMethod.GET, null,new ParameterizedTypeReference<List<Project>>(){});
        return res.getBody();
    }
    
    public Project createDataProject(Project project){
        ResponseEntity<Project> proj = restTemplate.postForEntity(baseUrl+"/create", project, Project.class);
        return proj.getBody();
    }
    
    public String deleteDataProject(Long id){
        restTemplate.delete(baseUrl +"/delete/"+ id, Project.class);
        return "done";
    }
    
    public Project getProjectById(Long id){
        ResponseEntity<Project> proj = restTemplate.getForEntity(baseUrl + "/showbyid/" + id, Project.class);
        return proj.getBody();
    }
    
    public String updateDataProject(Long id, Project project){
        restTemplate.put(baseUrl + "/update/" + id, project, Project.class);
        return "done";
    }
}
