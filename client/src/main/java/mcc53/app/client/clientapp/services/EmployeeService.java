package mcc53.app.client.clientapp.services;

import mcc53.app.client.clientapp.models.Employee;
import mcc53.app.client.clientapp.models.ResponseData;
import mcc53.app.client.clientapp.models.ResponseSingle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author firmanzega
 */
@Service
public class EmployeeService {
    
    
    private RestTemplate restTemplate;

    @Value("${api.baseURL}/employee")
    private String baseUrl;
    
    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public ResponseData<Employee> getAll(){
        ResponseEntity<ResponseData<Employee>> response = restTemplate.exchange(baseUrl
                , HttpMethod.GET, null, 
                new ParameterizedTypeReference<ResponseData<Employee>>(){});
        
        return response.getBody();
    }
    
    public Employee create(Employee employee) {
        ResponseEntity<Employee> response
                = restTemplate.postForEntity(baseUrl, employee, Employee.class);
        return response.getBody();
    }
    
    public ResponseSingle<Employee> getById(Long id) {
        ResponseEntity<ResponseSingle<Employee>> response
                = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.GET, null,
                        new ParameterizedTypeReference<ResponseSingle<Employee>>() {
                });
        
        return response.getBody();
    }
    
    public void update(Long id, Employee employee) {
        System.out.println(employee);
        restTemplate.put(baseUrl + "/" + id, employee, Employee.class);
    }
    
    public void delete(Long id) {
        restTemplate.delete(baseUrl + "/" + id, Employee.class);
    }
    
}
