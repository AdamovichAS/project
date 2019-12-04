package com.github.adamovichas.project.service.config;

import com.github.adamovichas.project.dao.IBetDao;
import com.github.adamovichas.project.dao.ICashAccountDao;
import com.github.adamovichas.project.dao.IEventDao;
import com.github.adamovichas.project.dao.IUserDao;
import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.service.data.IDataBetService;
import com.github.adamovichas.project.service.data.IDataCashAccountService;
import com.github.adamovichas.project.service.data.IdataEventService;
import com.github.adamovichas.project.service.data.IdataUserService;
import com.github.adamovichas.project.service.data.impl.DataBetService;
import com.github.adamovichas.project.service.data.impl.DataCashAccountService;
import com.github.adamovichas.project.service.data.impl.DataEventService;
import com.github.adamovichas.project.service.data.impl.DataUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DaoConfig.class)
public class ServiceConfig {

    @Bean
    public IDataBetService betService(IBetDao betData){
        return new DataBetService(betData);
    }

    @Bean
    public IDataCashAccountService cashAccountService(ICashAccountDao cashAccountData){
        return new DataCashAccountService(cashAccountData);
    }

    @Bean
    public IdataEventService eventService(IEventDao dataEvent){
        return new DataEventService(dataEvent);
    }

    @Bean
    public IdataUserService userService(IUserDao dataUser){
        return new DataUserService(dataUser);
    }
}
