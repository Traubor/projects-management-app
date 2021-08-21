package com.elk.user.service;

import com.elk.persistance.model.User;
import com.elk.persistance.model.enums.Role;
import lombok.Getter;

@Getter
public class UserDto {

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.username = getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    private final Long id;
    private final String name;
    private final String surname;
    private final String email;
    private final String username;
    private final String password;
    private final Role role;
}
