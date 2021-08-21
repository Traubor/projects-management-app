package com.elk.project.service;

import com.elk.persistance.model.Project;
import com.elk.task.service.TaskDto;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class ProjectDto {

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.code = project.getCode();
        this.projectManagerId = project.getProjectManager().getId();
        this.tasks = project.getTasks().stream()
                .sorted()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }

    private final Long id;
    private final String name;
    private final String code;
    private final Long projectManagerId;
    private final List<TaskDto> tasks;
}
