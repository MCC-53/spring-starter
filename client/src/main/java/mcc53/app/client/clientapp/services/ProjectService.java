package mcc53.app.client.clientapp.services;

import mcc53.app.client.clientapp.models.Project;
import mcc53.app.client.clientapp.models.ResponseData;
import mcc53.app.client.clientapp.models.ResponseSingle;
import mcc53.app.client.clientapp.models.User;
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
 * @author firmanzega
 */
@Service
public class ProjectService {

    private RestTemplate restTemplate;

    @Value("${api.baseURL}/project")
    private String baseUrl;

    @Autowired
    public ProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseData<Project> getAll() {
        ResponseEntity<ResponseData<Project>> response = restTemplate.exchange(baseUrl,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseData<Project>>() {
        });

        return response.getBody();
    }

    public Project create(Project project) {
        ResponseEntity<Project> response
                = restTemplate.postForEntity(baseUrl, project, Project.class);
        return response.getBody();
    }

    public ResponseSingle<Project> getProjectByID(Long projectId) {
        ResponseEntity<ResponseSingle<Project>> response
                = restTemplate.exchange(baseUrl + "/" + projectId, HttpMethod.GET, null,
                        new ParameterizedTypeReference<ResponseSingle<Project>>() {
                });

        return response.getBody();
    }

    public void update(Long projectId, Project project) {
        restTemplate.put(baseUrl + "/" + projectId, project, Project.class);
    }

    public void delete(Long projectId) {
        restTemplate.delete(baseUrl + "/" + projectId, Project.class);
    }
}
