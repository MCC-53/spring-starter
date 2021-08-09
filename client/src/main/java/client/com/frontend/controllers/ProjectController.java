/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.com.frontend.controllers;

import client.com.frontend.models.Project;
import client.com.frontend.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author aceng
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
    public String getAllDataProject(Model model){
        model.addAttribute("project", projectService.getAllProject());
        return "project/index";
    }

    @GetMapping("/add")
    public String addProjectData(Model model){
        Project p = new Project();
        model.addAttribute("proj",p);
        return "project/add-project";
    }
    
    @PostMapping("/add")
    public String postDataProject(Project project){
        projectService.createDataProject(project);
        return "redirect:/project";
    }
    
    @GetMapping("/update/{id}")
    public String UpdateProject(Model model, @PathVariable("id") Long id){
        Project p = projectService.getProjectById(id);
//        model.addAttribute("proj",p);
        model.addAttribute("project",p);
        return "project/update-project";
    }
    
    @PostMapping("/update/{id}")
    public String updateDataProject(Project project, @PathVariable("id") Long id){
        projectService.updateDataProject(id,project);
        return "redirect:/project";
    }
    
    @GetMapping("/delete/{id}")
    public String postDataProject(@PathVariable("id") Long id){
        projectService.deleteDataProject(id);
        return "redirect:/project";
    }
}
