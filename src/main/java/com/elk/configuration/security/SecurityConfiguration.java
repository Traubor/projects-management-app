package com.elk.configuration.security;

import com.elk.domain.model.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
               // .loginPage("/login")
                //.loginProcessingUrl("/api/login")
                .successForwardUrl("/project/1")
                .permitAll().and()
        .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().hasAnyRole(Role.ADMINISTRATOR.name(), Role.PROJECT_MANAGER.name(), Role.DEVELOPER.name()).and()
        .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
