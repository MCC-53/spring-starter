/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;

import mcc53.client.app.models.Pokemon;
import mcc53.client.app.models.ResponseData;
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
 * @author haikal
 */
@Service
public class PokemonService {
    
    private RestTemplate restTemplate;
    
    @Value("${api.baseUrl}/pokemon")
    private String baseUrl;
    
    @Autowired
    public PokemonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public ResponseData<Pokemon> getAll() {
        ResponseEntity<ResponseData<Pokemon>> res = restTemplate.exchange(baseUrl, 
                HttpMethod.GET, null, 
                new ParameterizedTypeReference<ResponseData<Pokemon>>() {});
        
        return res.getBody();        
    }
    
}
