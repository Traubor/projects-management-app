package com.elk.domain.project.service;

import com.elk.domain.model.Project;
import com.elk.domain.task.service.TaskDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
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
