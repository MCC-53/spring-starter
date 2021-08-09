package mcc53.app.client.clientapp.services;

import mcc53.app.client.clientapp.models.Department;
import mcc53.app.client.clientapp.models.Employee;
import mcc53.app.client.clientapp.models.Register;
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
public class RegisterService {
    
    private RestTemplate restTemplate;

    @Value("${api.baseURL}/auth/register")
    private String baseUrl;
    
    @Autowired
    public RegisterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public Register create(Register register) {
        ResponseEntity<Register> response
                = restTemplate.postForEntity(baseUrl, register, Register.class);
        return response.getBody();
    }
    
//    public ResponseSingle<Department> getDepartmentById(Long id) {
//        ResponseEntity<ResponseSingle<Department>> response
//                = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<ResponseSingle<Department>>() {
//                });
//        
//        return response.getBody();
//    }
    
}
