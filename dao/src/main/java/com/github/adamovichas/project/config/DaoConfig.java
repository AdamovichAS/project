package com.github.adamovichas.project.config;

import com.github.adamovichas.project.dao.*;
import com.github.adamovichas.project.dao.impl.*;
import com.github.adamovichas.project.dao.repository.BetRepository;
import com.github.adamovichas.project.dao.repository.CashAccountRepository;
import com.github.adamovichas.project.dao.repository.EventRepository;
import com.github.adamovichas.project.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.github.adamovichas.project.dao.repository")
public class DaoConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CashAccountRepository cashAccountRepository;

    @Autowired
    private BetRepository betRepository;


    @Bean
    public IUserDao userDao(){
        return new UserDao(userRepository);
    }

    @Bean
    public ICashAccountDao cashDao(){
        return new CashAccountDao(cashAccountRepository);
    }

    @Bean
    public IEventDao eventDao(){
        return new EventDao(eventRepository);
    }

    @Bean
    public IBetDao betDao (){
        return new BetDao(betRepository);
    }

    
}
