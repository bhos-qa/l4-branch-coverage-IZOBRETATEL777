package com.example.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConfig {

    public static EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public static void closeEntityManagerFactory(EntityManagerFactory emf) {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

