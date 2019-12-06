package com.github.adamovichas.project.config;

import com.github.adamovichas.project.dao.*;
import com.github.adamovichas.project.dao.impl.*;
import com.github.adamovichas.project.dao.repository.*;
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
    private UserCashAccountRepository userCashAccountRepository;

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private AppCashAccountRepository appCashAccountRepository;

    @Autowired
    private UserPassportRepository userPassportRepository;

//    @Autowired
//    private EventStatisticRepository eventStatisticRepository;


    @Bean
    public IUserDao userDao(){
        return new UserDao(userRepository);
    }

    @Bean
    public IUserCashAccountDao cashDao(){
        return new UserCashAccountDao(userCashAccountRepository);
    }

    @Bean
    public IEventDao eventDao(){
        return new EventDao(eventRepository);
    }

    @Bean
    public IBetDao betDao (){
        return new BetDao(betRepository);
    }

    @Bean
    public ILeagueDao leagueDao(){
        return new LeagueDao(leagueRepository);
    }

    @Bean
    public IAppCashAccountDao appCashAccountDao(){
        return new AppCashAccountDao(appCashAccountRepository);
    }

    @Bean
    public IUserPassportDao userPassportDao(){
        return new UserPassportDao(userPassportRepository);
    }

//    @Bean
//    public IEventStatisticDao eventStatisticDao(){return new EventStatisticDao(eventStatisticRepository);}
}
