package com.elk.configuration.security;

import com.elk.domain.model.User;
import com.elk.domain.model.enums.Privilege;
import com.elk.domain.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class DefaultAuthenticationProviderTest {

    private DefaultAuthenticationProvider authenticationProvider;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private Authentication authentication;

    private final String username = "username";
    private final String password = "password";

    @BeforeEach
    void setUp() {
        authenticationProvider = new DefaultAuthenticationProvider(authenticationService);
        when(authentication.getName()).thenReturn(username);
        when(authentication.getCredentials()).thenReturn(password);
    }

    @Test
    @DisplayName("When authenticationService does not authenticate user then throw a DefaultAuthenticationCredentialsNotFoundException")
    void whenAuthenticationServiceDoesNotAuthenticateUser_throwException() {
        //given
        when(authenticationService.authenticate(username, password))
                .thenReturn(Optional.empty());

        //do + verify
        assertThrows(DefaultAuthenticationCredentialsNotFoundException.class,
                () -> authenticationProvider.authenticate(authentication));
    }

    @Test
    @DisplayName("When admin user is authenticated it should have all possible privileges and ADMINISTRATOR authority")
    void authenticatedUserWithAdministratorRoleShouldHaveAuthoritiesToAllPrivileges() {
        //given
        User user = createUserWithRole(username, password, Role.ADMINISTRATOR);
        when(authenticationService.authenticate(username, password))
                .thenReturn(Optional.of(user));

        //do
        Authentication result = authenticationProvider.authenticate(authentication);

        //verify
        assertThat(result.getAuthorities()).flatExtracting(GrantedAuthority::getAuthority)
                .containsAll(getAllMatchingPrivilegesNames(priv -> true));
        assertThat(result.getAuthorities()).flatExtracting(GrantedAuthority::getAuthority)
                .contains(Role.ADMINISTRATOR.name());
    }

    @Test
    @DisplayName("When project manager user is authenticated it should have specific privileges")
    void authenticatedUserWithProjectManagerRoleShouldHaveAuthoritiesToSpecificPrivileges() {
        //given
        User user = createUserWithRole(username, password, Role.PROJECT_MANAGER);
        when(authenticationService.authenticate(username, password))
                .thenReturn(Optional.of(user));

        //do
        Authentication result = authenticationProvider.authenticate(authentication);

        //verify
        assertThat(result.getAuthorities()).flatExtracting(GrantedAuthority::getAuthority)
                .containsAll(getAllMatchingPrivilegesNames(priv -> priv.contains("TASK")));
        assertThat(result.getAuthorities()).flatExtracting(GrantedAuthority::getAuthority)
                .containsAll(getAllMatchingPrivilegesNames(priv -> priv.contains("PROJECT")));
        assertThat(result.getAuthorities()).flatExtracting(GrantedAuthority::getAuthority)
                .contains(Role.PROJECT_MANAGER.name());

        assertThat(result.getAuthorities()).flatExtracting(GrantedAuthority::getAuthority)
                .doesNotContain(getAllMatchingPrivilegesNames(priv -> priv.contains("USER")));
        assertThat(result.getAuthorities()).flatExtracting(GrantedAuthority::getAuthority)
                .doesNotContain(Role.ADMINISTRATOR.name());
    }

    private User createUserWithRole(String username, String password, Role role) {
        User user = mock(User.class);
        when(user.getRole()).thenReturn(role);
        when(user.getUsername()).thenReturn(username);
        when(user.getPassword()).thenReturn(password);
        return user;
    }

    private List<String> getAllMatchingPrivilegesNames(Predicate<String> filter) {
        return Arrays.stream(Privilege.values())
                .map(Enum::name)
                .filter(filter)
                .collect(Collectors.toList());
    }
}