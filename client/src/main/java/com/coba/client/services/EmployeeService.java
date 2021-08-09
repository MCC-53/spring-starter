package com.coba.client.services;

import com.coba.client.models.Employee;
import com.coba.client.models.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {
    private RestTemplate restTemplate;

    @Value("${api.Url}/employee")
    private String apiUrl;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseMessage<Employee> getAlll(){
        ResponseEntity<ResponseMessage<Employee>> response = restTemplate.exchange
                (apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<ResponseMessage<Employee>>(){});
        return response.getBody();
    }

    public Employee create(Employee employee) {
        ResponseEntity<Employee> res = restTemplate.postForEntity(apiUrl, employee, Employee.class);
        return res.getBody();
    }

}
