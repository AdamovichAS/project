package com.github.adamovichas.project.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static EntityManagerFactory emFactory=null;

    public static EntityManager getEntityManager() {
        if (emFactory == null) {
            emFactory  = Persistence.createEntityManagerFactory("com.github.adamovichas.project");
        }
        return emFactory.createEntityManager();
    }

    public static void closeEMFactory() {
        emFactory.close();
    }
}
