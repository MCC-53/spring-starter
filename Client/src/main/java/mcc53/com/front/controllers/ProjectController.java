///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package mcc53.com.front.controllers;
//
//import mcc53.com.front.models.Project;
//import mcc53.com.front.services.ProjectService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// *
// * @author Xvitas
// */
//@Controller
//@RequestMapping("/project")
//public class ProjectController {
//
//    private ProjectService projectService;
//
//    @Autowired
//    public ProjectController(ProjectService projectService) {
//        this.projectService = projectService;
//    }
////==============================================================================
////    GETALL
//
//    @GetMapping
//    public String getAll(Model model) {
//        model.addAttribute("projectinAja", projectService.getAll());
//
//        return "/project/view";
//    }
////==============================================================================    
////    CREATE
//
//    @GetMapping("/add")
//    public String showForm(Model model) {
//        Project project = new Project();
//        model.addAttribute("project", project);
//        return "project/add-project";
//    }
//
//    @PostMapping("/add")
//    public String postProject(Project project) {
//        projectService.createProject(project);
//        return "redirect:/project";
//    }
////==============================================================================
////    UPDATE
//
//    @GetMapping("/update/{id}")
//    public String updateProject(Model model, @PathVariable("id") Integer id) {
//        Project p = projectService.getProjectById(id);
//        model.addAttribute("project", p);
//        return "project/update-project";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateProject(Project project, @PathVariable("id") Integer id) {
//        projectService.updateProject(id, project);
//        return "redirect:/project";
//    }
////==============================================================================
////    DELETE
//
//    @GetMapping("/delete/{id}")
//    public String postProject(@PathVariable("id") Integer id) {
//        projectService.deleteProject(id);
//        return "redirect:/project";
//    }
////==============================================================================
//}
