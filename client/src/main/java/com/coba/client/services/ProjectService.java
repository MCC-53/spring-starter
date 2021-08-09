package com.coba.client.services;

import com.coba.client.models.Department;
import com.coba.client.models.Project;
import com.coba.client.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProjectService {
    private RestTemplate restTemplate;

    @Value("${api.Url}/project")
    private String apiUrl;

    @Autowired
    public ProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseMessage<Project> getAlll(){
        ResponseEntity<ResponseMessage<Project>> response = restTemplate.exchange
                (apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference
                        <ResponseMessage<Project>>(){});
        return response.getBody();
    }

    public Project create(Project project){
        ResponseEntity<Project> response = restTemplate.postForEntity(apiUrl,project,Project.class);
        return response.getBody();
    }

}
