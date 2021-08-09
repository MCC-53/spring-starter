/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Department;
import mcc53.com.models.Employee;
import mcc53.com.models.ResponseMessage;
import mcc53.com.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author WahyuKu
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    
    @GetMapping//Get All Department
    public ResponseEntity<ResponseMessage<List<Department>>> getAll() {
        ResponseMessage<List<Department>> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Get All Department Data");
        responseMessage.setData(departmentService.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<ResponseMessage<Department>> getById(@PathVariable("id") Long id){
        ResponseMessage<Department> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Data id: "+id+" Found");
        responseMessage.setData(departmentService.getById(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<Department>> insert(@Valid @RequestBody
        Department department, Errors errors ){
        ResponseMessage<Department> responseMessage = new ResponseMessage<>();
        if (errors.hasErrors()){
            for (ObjectError error:errors.getAllErrors()){
                responseMessage.getMessage().add(error.getDefaultMessage());
            }
            responseMessage.setStatus(false);
            responseMessage.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
        else {
            responseMessage.setStatus(true);
            responseMessage.getMessage().add("Department Data Created Success");
            responseMessage.setData(departmentService.create(department));
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<Department>> update(@Valid  @PathVariable("id") Long id,
        @RequestBody Department department,Errors errors){
        ResponseMessage<Department> responseMessage = new ResponseMessage<>();
        if (errors.hasErrors()){
            for (ObjectError error:errors.getAllErrors()){
                responseMessage.getMessage().add(error.getDefaultMessage());
            }
            responseMessage.setStatus(false);
            responseMessage.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
        else {
                responseMessage.setStatus(true);
                responseMessage.getMessage().add("Update Success");
                responseMessage.setData((departmentService.update(id,department)));
                return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<Department>> delete(@PathVariable("id") Long id) {
        ResponseMessage<Department> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Delete Success");
        responseMessage.setData(departmentService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("likename/get")
    public ResponseEntity<ResponseMessage<List<Department>>> getLikeByName(@RequestBody String name){
        ResponseMessage<List<Department>> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Data Load Success");
        responseMessage.setData(departmentService.getDepartmentLikeName(name));
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("likename/count")
    public ResponseEntity<ResponseMessage<Long>> countLikeByName(@RequestBody String name){
        ResponseMessage<Long> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Count Success");
        responseMessage.setData(departmentService.countSameName(name));
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
