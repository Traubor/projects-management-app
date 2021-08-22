package com.elk.domain.task.service;

import com.elk.domain.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<TaskDto> getAllTasks() {
        return repository.findAll().stream()
                .sorted()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }
}
