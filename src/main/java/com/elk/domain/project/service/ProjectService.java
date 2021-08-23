package com.elk.domain.project.service;

import com.elk.common.controller.EntityNotFoundException;
import com.elk.domain.model.Project;
import com.elk.domain.model.User;
import com.elk.domain.project.repository.ProjectRepository;
import com.elk.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository,
                          UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Optional<ProjectDto> getProject(Long id) {
        return projectRepository.findById(id)
                .map(ProjectDto::new);
    }

    public List<ProjectHeadingDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .sorted()
                .map(ProjectHeadingDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public void updateProject(ProjectDto projectDto) {
        projectRepository.findById(projectDto.getId())
                .ifPresentOrElse(existingProject -> {
                            User projectManager = findProjectManager(projectDto);
                            existingProject.update(projectDto, projectManager);
                        },
                        () -> {
                            throw new EntityNotFoundException(Project.class, projectDto.getId());
                        });

    }

    private User findProjectManager(ProjectDto projectDto) {
        User projectManager = userRepository.findById(projectDto.getProjectManagerId())
                .orElseThrow(() -> new EntityNotFoundException(User.class, projectDto.getProjectManagerId()));
        return projectManager;
    }

    public void createProject(ProjectDto projectDto) {
        Project newProject = Project.builder()
                .name(projectDto.getName())
                .code(projectDto.getCode())
                .projectManager(userRepository.getById(projectDto.getProjectManagerId()))
                .build();
        log.info("Project {}", newProject);
        projectRepository.save(newProject);
    }
}
