///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mcc53.com.front.services;
//
//import java.util.List;
//import mcc53.com.front.models.Employee;
//import mcc53.com.front.models.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
///**
// *
// * @author Xvitas
// */
//
//
// GA KEPAKE LAGI!
//
//@Service
//public class EmployeeService {
//
//    private RestTemplate restTemplate;
//
//    @Value("${api.baseUrl}employee")
//    private String baseUrl;
//
//    @Autowired
//    public EmployeeService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
////==============================================================================
////    GET ALL
//    public List<Employee> getAll() {
//        ResponseEntity<List<Employee>> res = restTemplate
//                .exchange(baseUrl, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<List<Employee>>() {
//                });
//
//        return res.getBody();
//    }
////==============================================================================
////    GET BY ID
//
//    public Employee getById(Integer id) {
//        ResponseEntity<Employee> res = restTemplate
//                .getForEntity(baseUrl + "/" + id, Employee.class);
//
//        return res.getBody();
//    }
////==============================================================================
////    CREATE
//
//    public Employee create(Employee employee) {
//        ResponseEntity<Employee> res = restTemplate
//                .postForEntity(baseUrl, employee, Employee.class);
//
//        return res.getBody();
//    }
////==============================================================================
////    DELETE
//
//    public String delete(Integer id) {
//        restTemplate.delete(baseUrl + "/" + id, Employee.class);
//
//        return "delete sukses";
//    }
//
////==============================================================================
////    UPDATE
//    public String update(Integer id, Employee employee) {
//
//        restTemplate.put(baseUrl + "/" + id, employee, Employee.class);
//
//        return "update berhasil";
//    }
//}
