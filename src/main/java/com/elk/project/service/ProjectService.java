package com.elk.project.service;

import com.elk.persistance.model.Project;
import com.elk.project.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
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
}
