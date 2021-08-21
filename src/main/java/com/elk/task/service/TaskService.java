package com.elk.task.service;

import com.elk.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<TaskDto> getTask(Long id) {
        return repository.findById(id)
                .map(TaskDto::new);
    }
}
