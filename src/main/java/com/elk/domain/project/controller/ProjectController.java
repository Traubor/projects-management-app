package com.elk.domain.project.controller;

import com.elk.common.controller.EntityNotFoundException;
import com.elk.domain.model.Project;
import com.elk.domain.project.service.ProjectDto;
import com.elk.domain.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable Long id) {
        return projectService.getProject(id)
                .orElseThrow(() -> new EntityNotFoundException(Project.class, id));
    }

   // public List<ProjectDto>
}
