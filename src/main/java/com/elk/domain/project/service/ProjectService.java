package com.elk.domain.project.service;

import com.elk.domain.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository repository;

    @Autowired
    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Optional<ProjectDto> getProject(Long id) {
        return repository.findById(id)
                .map(ProjectDto::new);
    }

    public Optional<ProjectDto> getAllProjects(Long id) {
        return repository.findById(id)
                .map(ProjectDto::new);
    }
}
