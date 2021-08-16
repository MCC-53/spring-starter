/*
 * To change this license header, choose License Headers in Department Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mcc53.client.app.models.DepartmentData;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        ResponseEntity<List<DepartmentData>> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(createHeaders()), new ParameterizedTypeReference<List<DepartmentData>>() {});
        return responseEntity.getBody();
    } public DepartmentData getById(Integer id) {
        ResponseEntity<DepartmentData> responseEntity = restTemplate.getForEntity(baseUrl + "/" + id, DepartmentData.class);
        return responseEntity.getBody();
    } public DepartmentData create(DepartmentData departmentData) {
        ResponseEntity<DepartmentData> responseEntity = restTemplate.postForEntity(baseUrl, new HttpEntity<>(departmentData, createHeaders()), DepartmentData.class);
        return responseEntity.getBody();
    } public void update(DepartmentData departmentData) {
        restTemplate.put(baseUrl + "/" + departmentData.getId(), new HttpEntity<>(departmentData, createHeaders()), DepartmentData.class);
    } public void delete(Integer id) {
        restTemplate.delete(baseUrl + "/" + id, new HttpEntity<>(id, createHeaders()), DepartmentData.class);
    } public HttpHeaders createHeaders() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName(), password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }
}