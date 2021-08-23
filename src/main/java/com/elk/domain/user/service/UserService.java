package com.elk.domain.user.service;

import com.elk.common.utils.LabelsUtils;
import com.elk.domain.common.LabelWithId;
import com.elk.domain.model.enums.Role;
import com.elk.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<UserDto> getUser(Long id) {
        return repository.findById(id)
                .map(UserDto::new);
    }

    public List<UserDto> getAllUsers() {
        return repository.findAll().stream()
                .sorted()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public List<LabelWithId> getUserLabelsWithRole(Role role) {
        return repository.findAllByRole(role).stream()
                .sorted()
                .map(user -> new LabelWithId(user.getId(), LabelsUtils.userLabel(user)))
                .collect(Collectors.toList());
    }
}
