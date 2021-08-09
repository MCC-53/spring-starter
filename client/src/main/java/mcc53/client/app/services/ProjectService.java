/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;


import java.util.List;
import mcc53.client.app.models.Project;
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
public class ProjectService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.Url}/project")
    private String Url;

    @Autowired
    public ProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Project> getAll() {
        ResponseEntity<List<Project>> res = restTemplate.exchange(Url, HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Project>>() {});
        return res.getBody();
    }
    
    public Project create(Project project) {
        ResponseEntity<Project> res = restTemplate.postForEntity(Url, project, Project.class);
        return res.getBody();
    }
    
    public Project getProjectById(Integer projectId) {
        ResponseEntity<Project> proj = restTemplate.getForEntity(Url + "/showid/" + projectId, Project.class);
        return proj.getBody();
    }
    
    public String updateDataProject(Integer idProject, Project project) {
        restTemplate.put(Url + "/" + idProject, project, Project.class);
        return "done";
    }
    
    public String deleteDataProject(Integer projectId) {
        restTemplate.delete(Url + "/" + projectId, Project.class);
        return "done";
    }
    
}
