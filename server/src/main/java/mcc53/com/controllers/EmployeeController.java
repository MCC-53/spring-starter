/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import java.util.List;
import mcc53.com.models.Employee;
import mcc53.com.models.ResponseMessage;
import mcc53.com.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 * @author WahyuKu
 */
@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class EmployeeController {

    private EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping //Get All
    public ResponseEntity<ResponseMessage<List<Employee>>> getAlll() {
        ResponseMessage<List<Employee>> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Get All Employee Data");
        responseMessage.setData(employeeService.getAlll());
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}") //Get By Id
    public ResponseEntity<ResponseMessage<Employee>> getById(@PathVariable("id") Long id) {
        ResponseMessage<Employee> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Employee Data id: "+id+" Found");
        responseMessage.setData(employeeService.getById(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PreAuthorize("hasAuthority('CREATE')")
    @PostMapping //Post
    public ResponseEntity<ResponseMessage<Employee>> create(@Valid @RequestBody Employee employee, Errors errors) {
        ResponseMessage<Employee> responseMessage = new ResponseMessage<>();
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
            responseMessage.getMessage().add("Data Created Succes");
            responseMessage.setData(employeeService.create(employee));
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }
    }

    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping("/{id}") //Update
    public ResponseEntity<ResponseMessage<Employee>> update(@Valid @PathVariable("id") Long id,
            @RequestBody Employee employee, Errors errors) {
        ResponseMessage<Employee> responseMessage = new ResponseMessage<>();
        if (errors.hasErrors()){
            for (ObjectError error:errors.getAllErrors()){
                //Masukkan pesan default error ke get message response data
                responseMessage.getMessage().add(error.getDefaultMessage());
            }
            responseMessage.setStatus(false);
            responseMessage.setData(null);
            //return http status, dan body yg berisi response data
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
        }
        else {
            responseMessage.setStatus(true);
            responseMessage.getMessage().add("Data Update Succes");
            responseMessage.setData(employeeService.update(id, employee));//Panggil method save yang telah kita buat
            //Data diisi dengan produk yang kita save ke API
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);

        }
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{id}") //Delete
    public ResponseEntity<Employee> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(employeeService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/complete/address") //Get Adress is Not Null
    public ResponseEntity<ResponseMessage<List<Employee>>> getCompleteAddress(){
        ResponseMessage<List<Employee>> responseMessage = new ResponseMessage<>();
        List<Employee> temp = employeeService.searchAddressCompleteAddress();
        responseMessage.setStatus(true);
        if (temp.isEmpty()){
            responseMessage.getMessage().add("Succes Get Data from Employee");
            responseMessage.getMessage().add("But Data is Empty");
        }
        else {
            responseMessage.getMessage().add("Get Employee Data");
            responseMessage.setData(temp);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("complete/email") //Get Correct Email Format
    public ResponseEntity<ResponseMessage<List<String>>> getCompleteFormatEmail(){
        ResponseMessage<List<String>> responseMessage = new ResponseMessage<>();
        List<String> temp = employeeService.validFormatEmail();
        responseMessage.setStatus(true);
        if (temp.isEmpty()){
            responseMessage.getMessage().add("Data is Empty");
        }
        else {
            responseMessage.getMessage().add("Get Valid Format Email");
            responseMessage.setData(temp);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

}
