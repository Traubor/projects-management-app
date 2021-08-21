package com.elk.configuration.security;

import com.elk.domain.model.User;

import java.util.Optional;

public interface AuthenticationService {
    Optional<User> authenticate(String username, String password);
}
