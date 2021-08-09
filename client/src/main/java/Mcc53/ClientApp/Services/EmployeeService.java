/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mcc53.ClientApp.Services;

import Mcc53.ClientApp.Models.Employee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author putug
 */
@Service
public class EmployeeService {
    
    @Value("${api.mcc53serverSideUrl}/employee")
    private String _serverUrl;
    private RestTemplate _restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this._restTemplate = restTemplate;
    }
    
    public List<Employee> getAll(){
        String endPoint = String.format("%s/show", _serverUrl);
        ResponseEntity<List<Employee>> respon = _restTemplate.exchange(
                endPoint, 
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>(){});
        
        return respon.getBody();
    }
}
