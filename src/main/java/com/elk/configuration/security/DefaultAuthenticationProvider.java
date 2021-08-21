package com.elk.configuration.security;

import com.elk.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationService authenticationService;

    @Autowired
    public DefaultAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<User> user = authenticationService.authenticate(authentication.getName(), (String) authentication.getCredentials());
        if (user.isPresent()) {
            AuthenticatedUser authenticatedUser = new AuthenticatedUser(user.get(), getAuthorities(user.get()));
            return new UsernamePasswordAuthenticationToken(authenticatedUser, authentication.getCredentials(), authenticatedUser.getAuthorities());
        } else {
            throw new AuthenticationCredentialsNotFoundException("Wrong credentials");
        }
    }

    private Collection<SimpleGrantedAuthority> getAuthorities(User user) {
        return Stream.concat(user.getRole().getPrivileges().stream(), Stream.of(user.getRole()))
                .map(priv -> new SimpleGrantedAuthority(priv.name()))
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
