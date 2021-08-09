///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mcc53.com.front.services;
//
//import java.util.List;
//import mcc53.com.front.models.Project;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
///**
// *
// * @author Xvitas
// */
//@Service
//public class ProjectService {
//
//    private RestTemplate restTemplate;
//
//    @Value("${api.baseUrl}project")
//    private String baseUrl;
//
//    @Autowired
//    public ProjectService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public List<Project> getAll() {
//        ResponseEntity<List<Project>> res = restTemplate
//                .exchange(baseUrl, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<List<Project>>() {
//                });
//
//        return res.getBody();
//    }
//
//    public Project createProject(Project project) {
//        ResponseEntity<Project> proj = restTemplate.postForEntity(baseUrl, project, Project.class);
//        return proj.getBody();
//    }
//
//    public String deleteProject(Integer id) {
//        restTemplate.delete(baseUrl + "/" + id, Project.class);
//        return "done";
//    }
//
//    public Project getProjectById(Integer id) {
//        ResponseEntity<Project> proj = restTemplate.getForEntity(baseUrl + "/" + id, Project.class);
//        return proj.getBody();
//    }
//
//    public String updateProject(Integer id, Project project) {
//        restTemplate.put(baseUrl + "/" + id, project, Project.class);
//
//        return "done";
//    }
//}
