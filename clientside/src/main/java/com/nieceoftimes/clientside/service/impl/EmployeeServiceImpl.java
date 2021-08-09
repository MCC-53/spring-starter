package com.nieceoftimes.clientside.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImpl {

    private RestTemplate restTemplate;

    @Value("${api.server.endpoint}/api/employee")
    private String baseUrl;

    @Autowired
    public EmployeeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



}
