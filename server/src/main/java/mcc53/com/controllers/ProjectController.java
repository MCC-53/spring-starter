/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.com.controllers;

import java.util.List;

import mcc53.com.models.Employee;
import mcc53.com.models.Project;
import mcc53.com.models.ResponseMessage;
import mcc53.com.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;

/**
 *
 * @author WahyuKu
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    
    @GetMapping
    public ResponseEntity<ResponseMessage<List<Project>>> getAll() {
        ResponseMessage<List<Project>> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Get All Project Data");
        responseMessage.setData(projectService.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<Project>> getById(@PathVariable("id") Long id){
        ResponseMessage<Project> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Project Data id: "+id+" Found");
        responseMessage.setData(projectService.getById(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PostMapping //Post
    public ResponseEntity<ResponseMessage<Project>> create(@Valid @RequestBody Project project, Errors errors) {
        ResponseMessage<Project> responseMessage = new ResponseMessage<>();
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
            responseMessage.setData(projectService.create(project));
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }
    }

    @PutMapping("/{id}") //Update
    public ResponseEntity<ResponseMessage<Project>> update(@Valid @PathVariable("id") Long id,
                                                            @RequestBody Project project, Errors errors) {
        ResponseMessage<Project> responseMessage = new ResponseMessage<>();
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
            //Panggil method save yang telah kita buat
            responseMessage.setData(projectService.update(id, project));
            //Data diisi dengan produk yang kita save ke API
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }
    }

    @DeleteMapping("/{id}") //Delete
    public ResponseEntity<ResponseMessage<Project>> delete(@PathVariable("id") Long id) {
        ResponseMessage<Project> responseMessage = new ResponseMessage<>();
        responseMessage.setStatus(true);
        responseMessage.getMessage().add("Delete Succes");
        responseMessage.setData(projectService.delete(id));
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("/select/join/{id}")
    public ResponseEntity<ResponseMessage<Project>> joinSel(@PathVariable("id") Long id){
        ResponseMessage<Project> responseMessage = new ResponseMessage<>();
        Project temp = projectService.checkIdS(id);
        if (temp == null){
            responseMessage.setStatus(false);
            responseMessage.getMessage().add("Id Not Found");
            responseMessage.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
        }
        else{
            responseMessage.setStatus(true);
            responseMessage.getMessage().add("Id Found");
            responseMessage.setData(temp);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        }
    }

}
