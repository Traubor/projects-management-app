package com.elk.configuration.security;

import com.elk.domain.model.User;
import com.elk.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public DefaultAuthenticationService(PasswordEncoder passwordEncoder,
                                        UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    if(BCrypt.checkpw(password, user.getPassword())){
                        return user;
                    } else {
                        return null;
                    }
                });
    }
}
