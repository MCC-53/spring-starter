package com.coba.client.services;

import com.coba.client.models.Department;
import com.coba.client.models.Employee;
import com.coba.client.models.ResponseMessage;
import com.coba.client.models.ResponseSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DepartmentService {
    private RestTemplate restTemplate;

    @Value("${api.Url}/department")
    private String apiUrl;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseMessage<Department> getAlll(){
        ResponseEntity<ResponseMessage<Department>> response = restTemplate.exchange
                (apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference
                        <ResponseMessage<Department>>(){});
        return response.getBody();
    }

    public ResponseSingle<Department> getById(Long id) {
        ResponseEntity<ResponseSingle<Department>> res = restTemplate
                .exchange(apiUrl + "/" + id,HttpMethod.GET,null, new ParameterizedTypeReference
                        <ResponseSingle<Department>>(){});
        return res.getBody();
    }

    public Department create(Department department) {
        ResponseEntity<Department> res = restTemplate.postForEntity(apiUrl, department, Department.class);
        return res.getBody();
    }

    public String del(Long id) {
        restTemplate.delete(apiUrl +"/"+ id, Department.class);
        return "deleted";
    }

    public Department del_ajax(Long id) {
        restTemplate.delete(apiUrl +"/"+ id, Department.class);
        return null;
    }
    public ResponseMessage<Department> getAll_Ajax() {
        ResponseEntity<ResponseMessage<Department>> res = restTemplate
                .exchange(apiUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<ResponseMessage<Department>>(){});

        return res.getBody();
    }


}
