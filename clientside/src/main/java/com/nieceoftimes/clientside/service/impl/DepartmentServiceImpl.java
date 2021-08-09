package com.nieceoftimes.clientside.service.impl;

import com.nieceoftimes.clientside.model.Department;
import com.nieceoftimes.clientside.model.ResponseData;
import com.nieceoftimes.clientside.model.ResponseListData;
import com.nieceoftimes.clientside.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private RestTemplate restTemplate;

    @Autowired
    public DepartmentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${api.server.endpoint}/department")
    private String baseUrl;

    @Override
    public Department createDepartment(Department department) {
        ResponseEntity<Department> departmentCreateResponse = restTemplate
                .postForEntity(baseUrl, department, Department.class);

        return departmentCreateResponse.getBody();
    }

    @Override
    public ResponseListData<Department> readAllDepartment() {
        ResponseEntity<ResponseListData<Department>> departmentAllRetrieveResponse = restTemplate
                .exchange(baseUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<ResponseListData<Department>>() {});

        return departmentAllRetrieveResponse.getBody();
    }

    @Override
    public ResponseData<Department> readDepartmentById(String id) {
        ResponseEntity<ResponseData<Department>> departmentRetrieveResponse = restTemplate
                .exchange(baseUrl + "/" + id, HttpMethod.GET, null,
                        new ParameterizedTypeReference<ResponseData<Department>>() {});

        return departmentRetrieveResponse.getBody();
    }

    @Override
    public void updateDepartmentById(String id, Department department) {
        restTemplate
                .put(baseUrl + "/" + id, department, Department.class);
    }

    @Override
    public void deleteDepartmentById(String id) {
        restTemplate
                .delete(baseUrl + "/" + id, Department.class);
    }

}
