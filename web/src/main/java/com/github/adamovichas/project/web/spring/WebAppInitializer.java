package com.github.adamovichas.project.web.spring;

import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.service.config.ServiceConfig;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;



public class WebAppInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {WebConfig.class, ServiceConfig.class, DaoConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

//    @Override
//    protected Filter[] getServletFilters() {
//        final DelegatingFilterProxy delegateFilterProxy = new DelegatingFilterProxy();
//        delegateFilterProxy.setTargetBeanName("springSecurityFilterChain");
//        return new Filter[]{delegateFilterProxy};
//    }

    }

