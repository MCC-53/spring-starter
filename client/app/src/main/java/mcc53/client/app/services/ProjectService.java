/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;
import java.nio.charset.Charset;
import java.util.List;
import mcc53.client.app.models.ProjectData;
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
 * @author ACER
 */
@Service
public class ProjectService {
    private RestTemplate restTemplate;
    @Value("${api.baseUrl}/projects")
    private String baseUrl;
    @Autowired
    public ProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    } public List<ProjectData> getAll() {
        ResponseEntity<List<ProjectData>> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(createHeaders()), new ParameterizedTypeReference<List<ProjectData>>() {});
        return responseEntity.getBody();
    } public ProjectData getById(Integer id) {
        ResponseEntity<ProjectData> responseEntity = restTemplate.getForEntity(baseUrl + "/" + id, ProjectData.class);
        return responseEntity.getBody();
    } public ProjectData create(ProjectData projectData) {
        ResponseEntity<ProjectData> responseEntity = restTemplate.postForEntity(baseUrl, projectData, ProjectData.class);
        return responseEntity.getBody();
    } public void update(ProjectData projectData) {
        restTemplate.put(baseUrl + "/" + projectData.getId(), projectData, ProjectData.class);
    } public void delete(Integer id) {
        restTemplate.delete(baseUrl + "/" + id, ProjectData.class);
    } public HttpHeaders createHeaders() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName(), password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }
}