package com.elk.domain.user.repository;

import com.elk.domain.model.User;
import com.elk.domain.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findAllByRole(Role role);
}
