package com.github.adamovichas.project.web.spring;

import com.github.adamovichas.project.service.config.ServiceConfig;
import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.service.data.ICashAccountService;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import com.github.adamovichas.project.web.controller.AuthentificationController;
import com.github.adamovichas.project.web.controller.MainPageController;
import com.github.adamovichas.project.web.controller.admin.AdminController;
import com.github.adamovichas.project.web.controller.admin.event.EventController;
import com.github.adamovichas.project.web.controller.user.UserController;
import com.github.adamovichas.project.web.controller.user.bet.BetController;
import com.github.adamovichas.project.web.controller.user.cashier.CashierController;
import com.github.adamovichas.project.web.service.IServiceUtil;
import com.github.adamovichas.project.web.service.ServiceUtil;
import com.github.adamovichas.project.web.validation.EventValidation;
import com.github.adamovichas.project.web.validation.IEventValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = {"com.github.adamovichas.project.web"})
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
    IEventValidation eventValidation(){return new EventValidation();
    }

    @Bean
    MainPageController mainPageController(IEventService eventService){return new MainPageController(eventService);}

    @Bean
    AuthentificationController authentificationController(IUserService userService, IServiceUtil serviceUtil){
        return new AuthentificationController(userService,serviceUtil);
    }

    @Bean
    AdminController adminController(ICashAccountService cashAccountService,IEventService eventService){
        return new AdminController(cashAccountService, eventService);
    }

    @Bean
    UserController userController(IUserService userService, IBetService betService){
        return new UserController(userService,betService);
    }

    @Bean
    CashierController cashierController(ICashAccountService cashAccountService){
        return new CashierController(cashAccountService);
    }

    @Bean
    EventController eventController(IEventService eventService, IEventValidation eventValidation, ICashAccountService cashAccountService){
        return new EventController(eventService,eventValidation,cashAccountService);
    }

    @Bean
    BetController betController(IEventService eventService, IEventUtil eventUtil, IBetService betService, ICashAccountService cashAccountService){
        return new BetController(eventService,eventUtil,betService,cashAccountService);
    }

    @Bean
    ViewResolver viewResolver () {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
