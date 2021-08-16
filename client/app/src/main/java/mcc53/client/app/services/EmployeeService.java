/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.services;
import java.nio.charset.Charset;
import java.util.List;
import mcc53.client.app.models.EmployeeData;
import mcc53.client.app.models.EmployeeDto;
import mcc53.client.app.models.ProjectData;
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
 * @author ACER
 */
@Service
public class EmployeeService {
    private RestTemplate restTemplate;
    @Value("${api.baseUrl}/employees")
    private String baseUrl;
    @Value("${api.baseUrl}/users/create-register")
    private String baseUrlCreate;
    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    } public List<EmployeeData<ProjectData>> getAll() {
        ResponseEntity<List<EmployeeData<ProjectData>>> responseEntity = restTemplate.exchange(baseUrl, HttpMethod.GET, new HttpEntity<>(createHeaders()), new ParameterizedTypeReference<List<EmployeeData<ProjectData>>>() {});
        return responseEntity.getBody();
    } public EmployeeData<ProjectData> getById(Integer id) {
        ResponseEntity<EmployeeData<ProjectData>> responseEntity = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.GET, new HttpEntity<>(createHeaders()), new ParameterizedTypeReference<EmployeeData<ProjectData>>() {});
        return responseEntity.getBody();
    } public EmployeeDto create(EmployeeDto employeeDto) {
        ResponseEntity<EmployeeDto> responseEntity = restTemplate.postForEntity(baseUrl, new HttpEntity<>(employeeDto, createHeaders()), EmployeeDto.class);
        return responseEntity.getBody();
    } public EmployeeData<ProjectData> update(EmployeeData<ProjectData> employeeData) {
        ResponseEntity<EmployeeData<ProjectData>> responseEntity = restTemplate.exchange(baseUrl + "/" + employeeData.getId(), HttpMethod.PUT, new HttpEntity<>(createHeaders()), new ParameterizedTypeReference<EmployeeData<ProjectData>>() {});
        return responseEntity.getBody();
    } public EmployeeData<ProjectData> delete(Integer id) {
        ResponseEntity<EmployeeData<ProjectData>> responseEntity = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE, new HttpEntity<>(createHeaders()), new ParameterizedTypeReference<EmployeeData<ProjectData>>() {});
        return responseEntity.getBody();
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