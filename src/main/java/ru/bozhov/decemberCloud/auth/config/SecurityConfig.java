package ru.bozhov.decemberCloud.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.bozhov.decemberCloud.auth.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserService userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/login", "/registration", "/welcome")
                        .permitAll()
        ).formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                .loginPage("/login")
                .permitAll()
        ).logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                .logoutUrl("/logout")
                .permitAll());

        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

}
