package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
@RepositoryRestResource(exported = false) // no longer expose this API ---> disable
// you can still see the data, but unable to update them
public interface TicketTypeJpaRepository extends JpaRepository<TicketType, String> { // because the PK is String type
    List<TicketType> findByIncludesWorkshopTrue();
}
