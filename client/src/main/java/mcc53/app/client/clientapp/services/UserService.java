package mcc53.app.client.clientapp.services;

import java.util.List;
import mcc53.app.client.clientapp.models.ResponseData;
import mcc53.app.client.clientapp.models.User;
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
public class UserService {
    
    private RestTemplate restTemplate;

    @Value("${api.baseURL}/users")
    private String baseUrl;
    
    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public ResponseData<User> getAll(){
        ResponseEntity<ResponseData<User>> response = restTemplate.exchange(baseUrl
                , HttpMethod.GET, null, 
                new ParameterizedTypeReference<ResponseData<User>>(){});
        
        return response.getBody();
    }
}
