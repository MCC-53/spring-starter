/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MCC53.client.clientapp.controllers;

import com.MCC53.client.clientapp.models.Project;
import com.MCC53.client.clientapp.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    
    @Autowired
    ProjectService projectService;
    //==============================================================================
    
    //getAll
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("project",projectService.getAll());
        
        return "/project/view-project";
    }
    //getbyid
//     @GetMapping("/{id}")
//    public String getById (@PathVariable ("id") Long id, Model model){
//        model.addAttribute("project",projectService.getByIdProject(id));
//        
//        return "/project/get-by-id";
//    }
    //==============================================================================
    //create form project
    @GetMapping("/add")
    public String addForm (Model model){
        Project project = new Project();
        model.addAttribute("project",project);
        
        return "/project/form-project";
    }
    //add data project
    @PostMapping("/add")
    public String postProject (@ModelAttribute("project")Project project){
        projectService.createProject(project);
        
        return "redirect:/project";
    }
    //==============================================================================

    //update form
    @GetMapping("/update/{id}")
    public String updateDataProject (Model model,@PathVariable("id") Long id){
        
        Project p = projectService.getByIdProject(id);
        model.addAttribute("project",p);
        
        return "/project/update-project";
    }
    //proses update data
    @PostMapping("/update/{id}")
    public String updateDataProject (Project project , @PathVariable ("id") Long id){
        
        projectService.updateProject(id, project);
        
        return "redirect:/project";
    }
    //==============================================================================

    @GetMapping("/delete/{id}")
    public String deleteProject (@PathVariable("id") Long id){
        projectService.deleteProject(id);
        
        return "redirect:/project";
    }
    //==============================================================================

}
