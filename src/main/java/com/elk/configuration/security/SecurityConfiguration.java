package com.elk.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DefaultAuthenticationSuccessHandler successHandler;
    private final DefaultAuthenticationFailureHandler failureHandler;

    @Autowired
    public SecurityConfiguration(DefaultAuthenticationSuccessHandler successHandler,
                                 DefaultAuthenticationFailureHandler failureHandler) {
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/api/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll().and()
        .authorizeRequests()
                .antMatchers("/login**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/vendor**").permitAll()
                .antMatchers("/common**").permitAll()
                .antMatchers("/inline**").permitAll()
                .antMatchers("/polyfills**").permitAll()
                .antMatchers("/styles**").permitAll()
                .antMatchers("/runtime**").permitAll()
                .antMatchers("/main**").permitAll()
                .antMatchers("/*.woff2").permitAll()
                .antMatchers("/*.ttf").permitAll()
                .antMatchers("/*.woff").permitAll()
                .antMatchers("/*.svg").permitAll()
                .antMatchers("/*.eot").permitAll()
                .anyRequest().authenticated().and()
        .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
