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
    private TeamRepository teamRepository;

    @Autowired
    private AppCashAccountRepository appCashAccountRepository;

    @Autowired
    private UserPassportRepository userPassportRepository;

    @Autowired
    private EventStatisticRepository eventStatisticRepository;

    @Autowired
    private FactorRepository factorRepository;


    @Bean
    public IEventDao eventDao(){
        return new EventDao(eventRepository,factorRepository,eventStatisticRepository,leagueRepository);
    }

    @Bean
    public IUserDao userDao(){
        return new UserDao(userRepository,userPassportRepository,userCashAccountRepository);
    }


    @Bean
    public IBetDao betDao (){
        return new BetDao(betRepository,userRepository,factorRepository);
    }

    @Bean
    public ILeagueDao leagueDao(){
        return new LeagueDao(leagueRepository,teamRepository);
    }

    @Bean
    public IAppCashAccountDao appCashAccountDao(){
        return new AppCashAccountDao(appCashAccountRepository);
    }

}
