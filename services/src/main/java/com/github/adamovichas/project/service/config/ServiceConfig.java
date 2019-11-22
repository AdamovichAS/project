package com.github.adamovichas.project.service.config;

import com.github.adamovichas.project.IBetData;
import com.github.adamovichas.project.ICashAccountData;
import com.github.adamovichas.project.IDataEvent;
import com.github.adamovichas.project.IDataUser;
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
    public IDataBetService betService(IBetData betData){
        return new DataBetService(betData);
    }

    @Bean
    public IDataCashAccountService cashAccountService(ICashAccountData cashAccountData){
        return new DataCashAccountService(cashAccountData);
    }

    @Bean
    public IdataEventService eventService(IDataEvent dataEvent){
        return new DataEventService(dataEvent);
    }

    @Bean
    public IdataUserService userService(IDataUser dataUser){
        return new DataUserService(dataUser);
    }
}
