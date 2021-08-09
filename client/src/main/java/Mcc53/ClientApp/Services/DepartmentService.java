/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mcc53.ClientApp.Services;

import Mcc53.ClientApp.Models.Department;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
                null, 
                new ParameterizedTypeReference<List<Department>>() { });
        
        return respon.getBody();
    }
    
    public Department create(Department newData){
        String endpoint = String.format("%s/create", _serverUrl);
        // Sesuaikan dengan apa yang dibutuhkan API postnya
        newData.setId(null);
        HttpEntity<Department> request = new HttpEntity(newData);
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
                null, 
                Department.class);
        
        return respon.getBody();
    }

    public Department deleteById(int id) {
        String endPoint = String.format("%s/delete/%d", _serverUrl, id);
        ResponseEntity<Department> respon = _restTemplate.exchange(
                endPoint, 
                HttpMethod.DELETE, 
                null, 
                Department.class);
        
        return respon.getBody();
    }

    public Department updateById(Department updatedData) {
        String endPoint = String.format("%s/update/%d", _serverUrl, updatedData.getId());
        HttpEntity<Department> request = new HttpEntity(updatedData);
        ResponseEntity<Department> respon = _restTemplate.exchange(
                endPoint, 
                HttpMethod.PUT, 
                request, 
                Department.class);
        
        return respon.getBody();
    }
}
