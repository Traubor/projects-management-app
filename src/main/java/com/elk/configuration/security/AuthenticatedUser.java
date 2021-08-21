package com.elk.configuration.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AuthenticatedUser extends User {

    private final Long userId;

    public AuthenticatedUser(com.elk.domain.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        userId = user.getId();
    }
}
