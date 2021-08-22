package com.elk.configuration.security;

import lombok.Getter;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

@Getter
public class DefaultAuthenticationCredentialsNotFoundException extends AuthenticationCredentialsNotFoundException {

    private final String errorCode;

    public DefaultAuthenticationCredentialsNotFoundException(String errorCode) {
        super("Failed to authenticate");
        this.errorCode = errorCode;
    }
}
