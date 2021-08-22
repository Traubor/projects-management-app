package com.elk.domain.project.service;

import com.elk.domain.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ProjectHeadingDto> getAllProjects() {
        return repository.findAll().stream()
                .sorted()
                .map(ProjectHeadingDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
