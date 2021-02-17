package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketTypeJpaRepository extends JpaRepository<TicketType, String> { // because the PK is String type
    List<TicketType> findByIncludesWorkshopTrue();
}
