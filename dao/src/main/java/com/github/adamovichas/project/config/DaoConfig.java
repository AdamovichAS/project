package com.github.adamovichas.project.config;

import com.github.adamovichas.project.dao.*;
import com.github.adamovichas.project.dao.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class DaoConfig {

    @Bean
    public ISessionHibernate session(EntityManagerFactory emf){
        return new SessionHibernate(emf);
    }
    @Bean
    public IDataUser userDao(ISessionHibernate session){
        return new DataUser(session);
    }

    @Bean
    public ICashAccountData cashDao(ISessionHibernate session){
        return new CashAccountData(session);
    }

    @Bean
    public IDataEvent eventDao(ISessionHibernate session){
        return new DataEvent(session);
    }

    @Bean
    public IBetData betDao (ISessionHibernate session){
        return new BetData(session);
    }

}
