package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session, Long> {
    List<Session> findBySessionNameContains(String name);
    /**
     * Query DSL can begin with findBy, queryBy, readBy, countBy, getBy
     * Query DSL uses JPA attribute names for criteria
     * Multiple criteria combined with And/Or
     * Suffix `contains` ---> create a like query rather than an exact `equals` match*/

    Session findFirstBySessionNameContains(String name);
    Long countBySessionNameContains(String name);
    List<Session> findBySessionLengthNot(Integer sessionLength);

    // `Like` is like `Contains` but has a special string
    List<Session> findBySessionNameNotLike(String name);
}

// Proxy finder
