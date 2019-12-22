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
                                .antMatchers("/","/login","/registration","/main/").permitAll()
                                .antMatchers("/logout","/my_page","/update","/update_password","/update_passport").hasAnyRole("ADMIN","USER")
                                .antMatchers("/admin/**").hasRole("ADMIN")
                                .antMatchers("/user/").hasRole("USER")
                                .antMatchers("/user/bet/**","/user/cashier/**").hasAnyRole("VEREF_PASSED")
                                .anyRequest().authenticated())
                .csrf().disable();
    }
}
