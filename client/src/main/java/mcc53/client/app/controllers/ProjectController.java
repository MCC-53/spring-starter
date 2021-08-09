/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;

import mcc53.client.app.models.Project;
import mcc53.client.app.services.ProjectService;
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
 * @author haikal
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
        return "project/view";
    }
    
    @GetMapping("/add")
    public String showForm(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        return "project/form-project";
    }
    
    @PostMapping("/add")
    public String createDepartment(@ModelAttribute("project") Project project) {
        projectService.create(project);
        return ("redirect:/project");
    }
    
    @GetMapping("/update/{id}")
    public String UpdateProject(Model model, @PathVariable("id") Integer id) {
        Project p = projectService.getProjectById(id);
        model.addAttribute("project", p);
        return "project/update-project";        
    }
    
    @PostMapping("/update/{id}")
    public String updateDataProject(Project project, @PathVariable("id") Integer id) {
        projectService.updateDataProject(id,project);
        return "redirect:/project";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteDataProject(@PathVariable("id") Integer id) {
        projectService.deleteDataProject(id);
        return "redirect:/project";
    }
}
