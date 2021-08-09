/*
 * To change this license header, choose License Headers in Department Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;
import java.util.List;
import mcc53.client.app.models.DepartmentData;
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
public class DepartmentService {
    private RestTemplate restTemplate;
    @Value("${api.baseUrl}/departments")
    private String baseUrl;
    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    } public List<DepartmentData> getAll() {
        ResponseEntity<List<DepartmentData>> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<DepartmentData>>() {});
        return responseEntity.getBody();
    } public DepartmentData getById(Integer id) {
        ResponseEntity<DepartmentData> responseEntity = restTemplate.getForEntity(baseUrl + "/" + id, DepartmentData.class);
        return responseEntity.getBody();
    } public DepartmentData create(DepartmentData departmentData) {
        ResponseEntity<DepartmentData> responseEntity = restTemplate.postForEntity(baseUrl, departmentData, DepartmentData.class);
        return responseEntity.getBody();
    } public void update(DepartmentData departmentData) {
        restTemplate.put(baseUrl + "/" + departmentData.getId(), departmentData, DepartmentData.class);
    } public void delete(Integer id) {
        restTemplate.delete(baseUrl + "/" + id, DepartmentData.class);
    }
}