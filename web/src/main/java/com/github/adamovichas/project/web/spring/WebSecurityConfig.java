package com.github.adamovichas.project.web.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/**").permitAll()
//                                .antMatchers("/logout").hasAnyRole("ADMIN","USER_VER")
//                                .antMatchers("/admin/**").hasRole("ADMIN")
//                                .antMatchers("/user/bet/**","/user/cashier/**").hasRole("USER_VER")
                                .anyRequest().authenticated())
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling.accessDeniedPage("access_denied.jsp"))
                .csrf().disable();
    }
}
