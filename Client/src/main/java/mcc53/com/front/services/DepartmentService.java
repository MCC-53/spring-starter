///*
// * To change this license header, choose License Headers in Department Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mcc53.com.front.services;
//
//import java.util.List;
//import mcc53.com.front.models.Department;
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
////GA KEPAKE LAGI!
//
//@Service
//public class DepartmentService {
//
//    private RestTemplate restTemplate;
//
//    @Value("${api.baseUrl}department")
//    private String baseUrl;
//
//    @Autowired
//    public DepartmentService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
////==============================================================================
////    GET ALL
//    public List<Department> getAll() {
//        ResponseEntity<List<Department>> res = restTemplate
//                .exchange(baseUrl, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<List<Department>>() {
//                });
//
//        return res.getBody();
//    }
////==============================================================================
////    GET BY ID
//
//    public Department getById(Integer id) {
//        ResponseEntity<Department> res = restTemplate
//                .getForEntity(baseUrl + "/" + id, Department.class);
//
//        return res.getBody();
//    }
////==============================================================================
////    CREATE
//
//    public Department create(Department department) {
//        ResponseEntity<Department> res = restTemplate
//                .postForEntity(baseUrl, department, Department.class);
//
//        return res.getBody();
//    }
////==============================================================================
////    DELETE
//
//    public String delete(Integer id) {
//        restTemplate.delete(baseUrl + "/" + id, Department.class);
//
//        return "delete sukses";
//    }
//
////==============================================================================
////    UPDATE
//    public String update(Integer id, Department department) {
//
//        restTemplate.put(baseUrl + "/" + id, department, Department.class);
//
//        return "update berhasil";
//    }
//}
