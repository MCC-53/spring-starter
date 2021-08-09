package mcc53.app.client.clientapp.controllers;

import mcc53.app.client.clientapp.models.Project;
import mcc53.app.client.clientapp.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author firmanzega
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
    public String GetAll(Model model) {
        model.addAttribute("projects", projectService.getAll().getPayLoad());
        return "project/index";
    }

    @GetMapping("/add")
    public String create(Model model) {
        model.addAttribute("projectAdd", new Project());
        return "project/add-project";
    }

    @PostMapping("/add")
    public String saveCreate(Project project) {
        projectService.create(project);
        return "redirect:/projects";
    }
    
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("projectUpdate", projectService.getProjectByID(id).getPayLoad());
        return "project/update-project";
    }

    @PostMapping("/update/{id}")
    public String saveUpdate(@PathVariable("id") Long id, Project project) {
        projectService.update(id, project);
        return "redirect:/projects";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        projectService.delete(id);
        return "redirect:/projects";
    }
    
}
