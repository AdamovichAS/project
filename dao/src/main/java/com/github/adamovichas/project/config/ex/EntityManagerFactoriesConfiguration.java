package com.github.adamovichas.project.config.ex;

import com.github.adamovichas.project.config.HibernateConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Import(HibernateConfig.class)
public class EntityManagerFactoriesConfiguration {

    private final HibernateConfig hibernateConfig;

    public EntityManagerFactoriesConfiguration(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean emf() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
                emf.setDataSource(hibernateConfig.dataSource());
        emf.setPackagesToScan("com.github.adamovichas.project.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return emf;
    }
}
