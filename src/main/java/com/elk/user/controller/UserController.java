package com.elk.user.controller;

import com.elk.common.controller.EntityNotFoundException;
import com.elk.persistance.model.Project;
import com.elk.persistance.model.User;
import com.elk.task.service.TaskDto;
import com.elk.task.service.TaskService;
import com.elk.user.service.UserDto;
import com.elk.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
