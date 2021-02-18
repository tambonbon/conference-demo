package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class SessionCustomJpaRepositoryImpl implements SessionCustomJpaRepository{
    @Autowired
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Session> customGetSession() {
        return entityManager.createQuery("select s from sessions s").getResultList();
    }
}
