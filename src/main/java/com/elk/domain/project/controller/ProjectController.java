package com.elk.domain.project.controller;

import com.elk.common.controller.EntityNotFoundException;
import com.elk.domain.model.Project;
import com.elk.domain.project.service.ProjectDto;
import com.elk.domain.project.service.ProjectHeadingDto;
import com.elk.domain.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("{id}")
    public ProjectDto getProject(@PathVariable Long id) {
        return projectService.getProject(id)
                .orElseThrow(() -> new EntityNotFoundException(Project.class, id));
    }

    @GetMapping("all")
    public List<ProjectHeadingDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping("update")
    public void updateProject(@RequestBody ProjectDto projectDto) {
        projectService.updateProject(projectDto);
    }

    @PutMapping("create")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')") // this is an example how I would secure server side entry points
    public void createProject(@RequestBody ProjectDto projectDto) {
        projectService.createProject(projectDto);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}
