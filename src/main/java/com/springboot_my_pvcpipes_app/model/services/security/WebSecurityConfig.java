/**
 * 
 */
package com.springboot_my_pvcpipes_app.model.services.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.springboot_my_pvcpipes_app.model.services.user.CustomUserDetailsService;

/**
 * @author Dcruz
 * Configure Spring Security for Authentication (Login)
 * Listed pages that require authentication.
 * Implementation of PasswordEncoder that uses the BCrypt strong hashing function
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {	  
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/css/**", "/js/**", "/webjars/**").permitAll()
        .antMatchers("/", "/register", "/process_register", "/register_success").permitAll()
        .antMatchers("/login_success", "/update/{id}", "/edit/{id}", "/user/{id}", "/items/{id}", "/home", "/newItem/{id}", "/process_item", "/save_success", "/update_success").hasAnyAuthority("User", "Admin", "Customer")
        .antMatchers("/users", "/allItems").hasAnyAuthority("Admin")
        .anyRequest().authenticated()
        .and()
        .formLogin()
            .usernameParameter("email")
            .defaultSuccessUrl("/login_success")
            .permitAll()
        .and()
        .logout().logoutSuccessUrl("/").permitAll();
    }
}
