package com.github.adamovichas.project.config.ex;

import com.github.adamovichas.project.config.HibernateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@EnableTransactionManagement
@Import(EntityManagerFactoriesConfiguration.class)
public class TransactionManagersConfig {
    @Autowired
    EntityManagerFactoriesConfiguration emf;
    @Autowired
    private HibernateConfig hibernateConfig;

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm =
                new JpaTransactionManager();
        tm.setEntityManagerFactory(emf.emf().getNativeEntityManagerFactory());
        tm.setDataSource(hibernateConfig.dataSource());
        return tm;
    }
    @Bean
    public TransactionTemplate transactionTemplate(){
        return new TransactionTemplate(transactionManager());
    }
}
