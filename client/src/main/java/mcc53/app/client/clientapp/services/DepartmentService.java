package mcc53.app.client.clientapp.services;

import java.nio.charset.Charset;
import mcc53.app.client.clientapp.models.Department;
import mcc53.app.client.clientapp.models.ResponseData;
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
                HttpMethod.GET, new HttpEntity<>(createHeaders()),
                new ParameterizedTypeReference<ResponseData<Department>>() {
        });
        
        return response.getBody();
    }
    
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
