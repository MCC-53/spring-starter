/*
 * To change this license header, choose License Headers in Role Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;
import java.util.List;
import mcc53.client.app.models.RoleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
/**
 *
 * @author ACER
 */
@Service
public class RoleService {
    private RestTemplate restTemplate;
    @Value("${api.baseUrl}/roles")
    private String baseUrl;
    @Autowired
    public RoleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    } public List<RoleData> getAll() {
        ResponseEntity<List<RoleData>> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<RoleData>>() {});
        return responseEntity.getBody();
    } public RoleData getById(Integer id) {
        ResponseEntity<RoleData> responseEntity = restTemplate.getForEntity(baseUrl + "/" + id, RoleData.class);
        return responseEntity.getBody();
    } public RoleData create(RoleData roleData) {
        ResponseEntity<RoleData> responseEntity = restTemplate.postForEntity(baseUrl, roleData, RoleData.class);
        return responseEntity.getBody();
    } public void update(RoleData roleData) {
        restTemplate.put(baseUrl + "/" + roleData.getId(), roleData, RoleData.class);
    } public void delete(Integer id) {
        restTemplate.delete(baseUrl + "/" + id, RoleData.class);
    }
}