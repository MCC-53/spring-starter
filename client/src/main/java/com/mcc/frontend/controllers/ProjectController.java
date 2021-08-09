/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcc.frontend.controllers;

import com.mcc.frontend.models.Project;
import com.mcc.frontend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author HP
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("projects", projectService.getAll());
        return "project/index";
    }
    
    @GetMapping("/add")
    public String addProjectData(Model model){
        Project p = new Project();
        model.addAttribute("proj", p);
        return "project/add-project";
    }
    
    @PostMapping("/add")
    public String postDataProject(Project project){
        projectService.create(project);
        return "redirect:/project";
    }

    @GetMapping("/update/{id}")
    public String UpdateProject(@PathVariable("id") Integer id, Model model){
        Project p = projectService.getById(id);
        model.addAttribute("project", p);
        return "project/update-project";
    }
    
    @PostMapping("/update/{id}")
    public String updateDataProject(@PathVariable("id") Integer id, Project project){
        projectService.update(id, project);
        return "redirect:/project";
    }

    @GetMapping("/delete/{id}")
    public String postDataProject(@PathVariable("id") Integer id){
        projectService.delete(id);
        return "redirect:/project";
    }    
}
