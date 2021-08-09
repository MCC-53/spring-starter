package mcc53.app.client.clientapp.services;

import mcc53.app.client.clientapp.models.Department;
import mcc53.app.client.clientapp.models.ResponseData;
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
public class DepartmentService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.baseURL}/department")
    private String baseUrl;
    
    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public ResponseData<Department> getAll() {
        ResponseEntity<ResponseData<Department>> response = restTemplate.exchange(baseUrl,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<ResponseData<Department>>() {
        });
        
        return response.getBody();
    }
//    
//    public Department create(Department department) {
//        ResponseEntity<Department> response
//                = restTemplate.postForEntity(baseUrl, department, Department.class);
//        return response.getBody();
//    }
//    
//    public ResponseSingle<Department> getById(Long id) {
//        ResponseEntity<ResponseSingle<Department>> response
//                = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<ResponseSingle<Department>>() {
//                });
//        
//        return response.getBody();
//    }
//    
//    public Department update(Long id, Department department) {
//        restTemplate.put(baseUrl + "/" + id, department, Department.class);
//        return department;
//    }
//    
//    public Long delete(Long id) {
//        restTemplate.delete(baseUrl + "/" + id, Department.class);
//        return id;
//    }
    
}
