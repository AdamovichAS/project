package com.github.adamovichas.project.service.config;

import com.github.adamovichas.project.dao.*;
import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.service.data.ICashAccountService;
import com.github.adamovichas.project.service.data.IEventService;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.service.data.impl.BetService;
import com.github.adamovichas.project.service.data.impl.CashAccountService;
import com.github.adamovichas.project.service.data.impl.EventService;
import com.github.adamovichas.project.service.data.impl.UserService;
import com.github.adamovichas.project.service.util.IUtil;
import com.github.adamovichas.project.service.util.Util;
import com.github.adamovichas.project.service.util.event.EventUtil;
import com.github.adamovichas.project.service.util.event.IEventUtil;
import com.github.adamovichas.project.service.util.user.IUserUtil;
import com.github.adamovichas.project.service.util.user.UserUtil;
import com.github.adamovichas.project.service.util.user.passport.IUserPassportUtil;
import com.github.adamovichas.project.service.util.user.passport.UserPassportUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

//    private DaoConfig daoConfig;
//
//    public ServiceConfig(DaoConfig daoConfig) {
//        this.daoConfig = daoConfig;
//    }

    @Bean
    public IBetService betService(IBetDao betDao, IUserDao userDao){
        return new BetService(betDao,userDao);
    }

    @Bean
    public IEventService eventService(IEventDao eventDao, ILeagueDao leagueDao, IEventUtil eventUtil){
        return new EventService(eventDao,leagueDao, eventUtil);
    }

    @Bean
    public IUserService userService(IUserDao userDao){
        return new UserService(userDao, userUtil(), userPassportUtil());
    }

    @Bean
    public ICashAccountService cashAccountService(IBetDao betDao,IUserDao userDao, IAppCashAccountDao appCashAccountDao){
        return new CashAccountService(betDao,userDao,appCashAccountDao);
    }

    @Bean
    public IUserPassportUtil userPassportUtil(){return new UserPassportUtil();}

    @Bean
    public IUserUtil userUtil(){return new UserUtil();}

    @Bean
    public IEventUtil eventUtil(){return new EventUtil();}

//    @Bean
//    public IEventValidation eventValidation(){return new EventValidation();}

    @Bean
    public IUtil util(){return new Util();}
}
