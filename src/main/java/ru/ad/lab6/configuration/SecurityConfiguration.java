package ru.ad.lab6.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.ad.lab6.services.MyUserDetailsService;

@Configuration
@ComponentScan("ru.ad.lab6")
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

  private final MyUserDetailsService detailsService;

  @Autowired
  public SecurityConfiguration(MyUserDetailsService detailsService) {
    this.detailsService = detailsService;
  }

  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
        http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.userDetailsService(detailsService)
        .passwordEncoder(passwordEncoder());
    return authenticationManagerBuilder.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/users/login", "/error", "/users/register", "/users/process_reg").permitAll()
            .requestMatchers("/bicycles/*/edit", "/bicycles/new").hasRole("ADMIN")
            .anyRequest().hasAnyRole("USER", "ADMIN")
        )
        .formLogin(formLogin ->
            formLogin
                .loginPage("/users/login")
                .loginProcessingUrl("/users/process_login")
                .defaultSuccessUrl("/bicycles", true)
                .failureUrl("/users/login?error")
        )
        .logout(logout ->
            logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/users/login"));
    return http.build();
  }

}
