/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mcc53.ClientApp.Services;

import Mcc53.ClientApp.Models.Department;
import java.nio.charset.Charset;
import java.util.List;
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

/**
 *
 * @author putug
 */
@Service
public class DepartmentService {
    
    @Value("${api.mcc53serverSideUrl}/department")
    private String _serverUrl;
    private RestTemplate _restTemplate;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        _restTemplate = restTemplate;
    }
    
    public List<Department> getAll(){
        String endpoint = String.format("%s/show", _serverUrl);
        ResponseEntity<List<Department>> respon = _restTemplate.exchange(
                endpoint, 
                HttpMethod.GET, 
                new HttpEntity(createHeader()), 
                new ParameterizedTypeReference<List<Department>>() { });
        
        return respon.getBody();
    }
    
    public Department create(Department newData){
        String endpoint = String.format("%s/create", _serverUrl);
        // Sesuaikan dengan apa yang dibutuhkan API postnya
        newData.setId(null);
        HttpEntity<Department> request = new HttpEntity(newData, createHeader());
        ResponseEntity<Department> respon = _restTemplate.exchange(
                endpoint, 
                HttpMethod.POST, 
                request, 
                Department.class);
        return respon.getBody();
    }

    public Department getById(int id) {
        String endPoint = String.format("%s/showbyid/%d", _serverUrl, id);
        ResponseEntity<Department> respon = _restTemplate.exchange(
                endPoint, 
                HttpMethod.GET, 
                new HttpEntity<>(createHeader()), 
                Department.class);
        
        return respon.getBody();
    }

    public Department deleteById(int id) {
        String endPoint = String.format("%s/delete/%d", _serverUrl, id);
        ResponseEntity<Department> respon = _restTemplate.exchange(
                endPoint, 
                HttpMethod.DELETE, 
                new HttpEntity<>(createHeader()), 
                Department.class);
        
        return respon.getBody();
    }

    public Department updateById(Department updatedData) {
        String endPoint = String.format("%s/update/%d", _serverUrl, updatedData.getId());
        HttpEntity<Department> request = new HttpEntity(updatedData, createHeader());
        ResponseEntity<Department> respon = _restTemplate.exchange(
                endPoint, 
                HttpMethod.PUT, 
                request, 
                Department.class);
        
        return respon.getBody();
    }
    
    private HttpHeaders createHeader(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();

        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};

    }
}
