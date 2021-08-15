/*
 * To change this license header, choose License Headers in Department Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.front.services;

import mcc53.com.front.models.Department;
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

import java.nio.charset.Charset;
import java.util.List;

/**
 *
 * @author Xvitas
 */
@Service
public class DepartmentService {

//    private RestTemplate restTemplate;
//
//    @Autowired
//    public DepartmentService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @Value("${api.baseUrl}/department")
//    private String baseUrl;
//==============================================================================
//    GET ALL

//    public List<Department> getAll() {
//        ResponseEntity<List<Department>> res = restTemplate
//                .exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(createHeaders()),
//                        new ParameterizedTypeReference<List<Department>>() {
//                });
//
//        return res.getBody();
//    }
//==============================================================================
//    GET BY ID

//    public Department getById(Integer id) {
//        ResponseEntity<Department> res = restTemplate
//                .exchange(baseUrl + "/" + id, HttpMethod.GET, new HttpEntity<>(createHeaders()),
//                        new ParameterizedTypeReference<Department>() {
//                });
//
//        return res.getBody();
//    }

//    public Department getById(Integer id) {
//        ResponseEntity<Department> res = restTemplate
//                .getForEntity(baseUrl + "/" + id, Department.class);
//
//        return res.getBody();
//    }
//==============================================================================
//    CREATE
//    public Department create(Department department) {
//        ResponseEntity<Department> res = restTemplate
//                .exchange(baseUrl, HttpMethod.POST, new HttpEntity<>(createHeaders()),
//                        new ParameterizedTypeReference<Department>() {
//                });
//        return res.getBody();
//    }

//    public Department create(Department department) {
//        ResponseEntity<Department> res = restTemplate
//                .postForEntity(baseUrl, department, Department.class);
//
//        return res.getBody();
//    }
//==============================================================================
//    UPDATE
    
//    public String update(Integer id, Department department) {
//        ResponseEntity<Department> res = restTemplate
//                .exchange(baseUrl + "/" + id, HttpMethod.PUT, new HttpEntity<>(createHeaders()),
//                        new ParameterizedTypeReference<Department>() {
//                });
//        return "update berhasil";
//    }
    
//    public String update(Integer id, Department department) {
//
//        restTemplate.put(baseUrl + "/" + id, department, Department.class);
//
//        return "update berhasil";
//    }
//==============================================================================
//    DELETE

//    public String delete(Integer id) {
//        ResponseEntity<Department> res = restTemplate
//                .exchange(baseUrl + "/" + id, HttpMethod.DELETE, new HttpEntity<>(createHeaders()),
//                        new ParameterizedTypeReference<Department>() {
//                });
//
//        return "delete sukses";
//    }

//    public String delete(Integer id) {
//        restTemplate.delete(baseUrl + "/" + id, Department.class);
//
//        return "delete sukses";
//    }
//==============================================================================
    private HttpHeaders createHeaders() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();

        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }
}
