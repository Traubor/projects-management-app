package com.elk.domain.task.controller;

import com.elk.common.controller.EntityNotFoundException;
import com.elk.domain.model.Task;
import com.elk.domain.task.service.TaskDto;
import com.elk.domain.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable Long id) {
        return taskService.getTask(id)
                .orElseThrow(() -> new EntityNotFoundException(Task.class, id));
    }
}
