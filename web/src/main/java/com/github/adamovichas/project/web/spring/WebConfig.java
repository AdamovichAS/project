package com.github.adamovichas.project.web.spring;

import com.github.adamovichas.project.service.config.ServiceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig {

    private ServiceConfig serviceConfig;

    public WebConfig(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @Bean
    ViewResolver viewResolver () {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
