package com.github.adamovichas.project.web.spring;

import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.service.config.ServiceConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



//extends AbstractAnnotationConfigDispatcherServletInitializer
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
//    public void onStartup(ServletContext container) throws ServletException {
//        // Create the 'root' Spring application context
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(RootConfig.class);
//
//        // Listener for managing the lifecycle of the root application context
//        container.addListener(new ContextLoaderListener(rootContext));
//
//        // Create the dispatcher servlet's Spring application context
//        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//        dispatcherContext.register(WebConfig.class);
//
//        // Register and map the dispatcher servlet
//        ServletRegistration.Dynamic dispatcher =
//                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//        // Add separate mapping for welcome page
//        dispatcher.addMapping("/main");
//
//        // Setting container parameters
//        container.setInitParameter("defaultHtmlEscape", "true");
//
//        // Register character encoding filter
//        FilterRegistration charEncodingFilterReg =
//                container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
//        charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
//        charEncodingFilterReg.setInitParameter("forceEncoding", "true");
//        charEncodingFilterReg.addMappingForUrlPatterns(null, false, "/*");
    }

