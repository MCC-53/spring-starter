package com.mii.clinetapp.services;

import com.mii.clinetapp.models.Department;
import com.mii.clinetapp.models.Employee;
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
public class DepartmentService {

    public RestTemplate restTemplate;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.baseUrl}/department")
    private String baseUrl;

    public Collection<Department> getAll(){
        ResponseEntity<Collection<Department>> res = restTemplate
                .exchange(baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Collection<Department>>() {});
        return res.getBody();
    }

    public Department getById(Long id){
        ResponseEntity<Department> res = restTemplate.getForEntity(baseUrl+"/"+id, Department.class);

        return res.getBody();
    }

    public Department createDept(Department department) {
        ResponseEntity<Department> res = restTemplate
                .postForEntity(baseUrl, department, Department.class);
        return res.getBody();
    }

    public String update(Long id, Department department){
     restTemplate.put(baseUrl+"/"+id, department, Department.class);
        return "update success";
    }

    public String delete(Long id){
        restTemplate.delete(baseUrl+"/"+id,Department.class);
        return "delete success";
    }
}
