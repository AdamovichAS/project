package com.github.adamovichas.project.dao;

import com.github.adamovichas.project.ISessionHibernate;
import org.hibernate.Session;

import javax.persistence.EntityManagerFactory;

public class SessionHibernate implements ISessionHibernate {

    private final EntityManagerFactory factory;

    public SessionHibernate(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public Session getSession() {
        return factory.createEntityManager().unwrap(Session.class);
    }
}
