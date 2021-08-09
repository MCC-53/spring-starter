package com.nieceoftimes.clientside.service.impl;

import com.nieceoftimes.clientside.model.Project;
import com.nieceoftimes.clientside.model.ResponseListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProjectServiceImpl {
    private RestTemplate restTemplate;

    @Value("${api.server.endpoint}/api/project")
    private String baseUrl;

    @Autowired
    public ProjectServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Project> getAllProject() {
        ResponseEntity<ResponseListData<Project>> response = restTemplate
                .exchange(baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<ResponseListData<Project>>() {
                        });

        return response.getBody();
    }

    public Project createProject(Project project) {
        ResponseEntity<Project> response = restTemplate.postForEntity(baseUrl, project, Project.class);

        return response.getBody();
    }
}
