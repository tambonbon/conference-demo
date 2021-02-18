package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@RepositoryRestResource(path = "conference_sessions", collectionResourceRel = "conference_sessions") // custom name to avoid ambiguous
public interface SessionJpaRepository extends JpaRepository<Session, Long>, SessionCustomJpaRepository{
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

    // Enhanced JPQL syntax
    @Query(
            "SELECT s FROM sessions s where s.sessionName like %?1"
    )
    List<Session> getSessionWithNameEnhanced(String name);

    // Native SQL queries
    @Query(
            value = "SELECT * FROM sessions s WHERE s.sessionName = ?0", nativeQuery = true
    )
    List<Session> getSessionWithNameNative(String name);

    // Named Native SQL query is above TicketPrice entity

    // Modifiable Queries
    @Modifying
    @Query(
            "UPDATE sessions s set s.sessionName = ?1"
    )
    int updateSessionName(String name);

    // Paging and Sorting
    @Query(
            "SELECT s FROM sessions s WHERE s.sessionName like %:name"
    )
    Page<Session> getSessionWithName(@Param("name") String name, Pageable pageable);

}

