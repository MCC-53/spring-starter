/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcc53.client.app.controllers;
import mcc53.client.app.models.ProjectData;
import mcc53.client.app.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author ACER
 */
@Controller
@RequestMapping("/projects")
public class ProjectController {
    private ProjectService projectService;
    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("projects", projectService.getAll());
        return "project/get-all";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        return "project/get-by-id";
    }
    @GetMapping("/add-project")
    public String showFormInput(Model model) {
        ProjectData projectData = new ProjectData();
        model.addAttribute("project", projectData);
        return "project/input-project-form";
    }
    @GetMapping("/update-project/{id}")
    public String showFormUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("project", projectService.getById(id));
        return "project/update-project-form";
    }
    @PostMapping("/save-project")
    public String create(@ModelAttribute("project") ProjectData projectData) {
        projectService.create(projectData);
        return "redirect:/projects";
    }
    @PostMapping("/update-project")
    public String update(@ModelAttribute("project") ProjectData projectData) {
        projectService.update(projectData);
        return "redirect:/projects";
    }
    @GetMapping("/delete-project/{id}")
    public String delete(@PathVariable("id") Integer id) {
        projectService.delete(id);
        return "redirect:/projects";
    }
}