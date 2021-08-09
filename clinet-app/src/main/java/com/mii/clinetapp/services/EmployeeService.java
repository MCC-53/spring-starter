package com.mii.clinetapp.services;

import com.mii.clinetapp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Service
public class EmployeeService {
    public RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.baseUrl}/employee")
    private String baseUrl;

    public Collection<Employee> getAll(){
        ResponseEntity<Collection<Employee>> res = restTemplate.exchange(baseUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<Collection<Employee>>() {});
        return res.getBody();
    }

    public Employee getById(Long id){
        ResponseEntity<Employee> res = restTemplate.getForEntity(baseUrl+"/"+id, Employee.class);

        return res.getBody();
    }

    public Employee createEmp(Employee employee) {
        ResponseEntity<Employee> res = restTemplate
                .postForEntity(baseUrl, employee, Employee.class);
        return res.getBody();
    }

    public String update(Long id, Employee employee){
        restTemplate.put(baseUrl+"/"+id, employee, Employee.class);
        return "update success";
    }

    public String delete(Long id){
        restTemplate.delete(baseUrl+"/"+id, Employee.class);
        return "delete success";
    }
}
