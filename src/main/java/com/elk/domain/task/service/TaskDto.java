package com.elk.domain.task.service;

import com.elk.domain.model.Task;
import com.elk.domain.model.User;
import com.elk.domain.model.enums.Status;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter
public class TaskDto {

    public TaskDto(Task task) {
        this.id = task.getId();
        this.projectId = task.getProject().getId();
        this.assigneeId = Optional.ofNullable(task.getAssignee()).map(User::getId).orElse(null);
        this.description = task.getDescription();
        this.progress = task.getProgress();
        this.status = task.getStatus();
        this.deadline = task.getDeadline();
    }

    private final Long id;
    private final Long projectId;
    private final Long assigneeId;
    private final String description;
    private final BigDecimal progress;
    private final Status status;
    private final LocalDate deadline;
}
