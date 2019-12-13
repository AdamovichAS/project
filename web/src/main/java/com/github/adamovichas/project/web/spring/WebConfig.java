package com.github.adamovichas.project.web.spring;

import com.github.adamovichas.project.service.config.ServiceConfig;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.web.service.IServiceUtil;
import com.github.adamovichas.project.web.service.ServiceUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.github.adamovichas.project"})
public class WebConfig {

//    private ServiceConfig serviceConfig;
//
//    public WebConfig(ServiceConfig serviceConfig) {
//        this.serviceConfig = serviceConfig;
//    }

    @Bean
    IServiceUtil serviceUtil(IUserService userService){
        return new ServiceUtil(userService);
    }

    @Bean
    ViewResolver viewResolver () {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
