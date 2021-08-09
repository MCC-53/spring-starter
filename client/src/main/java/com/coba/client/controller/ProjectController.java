package com.coba.client.controller;

import com.coba.client.models.Department;
import com.coba.client.models.Project;
import com.coba.client.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("projects",projectService.getAlll());
        return "project/view";
    }

    @GetMapping("/add")
    public String showForm(Model model){
        Project project = new Project();
        model.addAttribute("project",project);
        return "project/form-project";
    }


}
