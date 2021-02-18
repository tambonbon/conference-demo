package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TicketPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.math.BigDecimal;
import java.util.List;


public interface TicketPriceJpaRepository extends JpaRepository<TicketPrice, Long> {
    @Query(
            "SELECT tp FROM TicketPrice tp WHERE tp.basePrice < ?1" +
                    " AND tp.ticketType.includesWorkshop = TRUE " // what is includeWorkshop?
    )
    List<TicketPrice> getTicketsUnderPriceWithWorkShops(BigDecimal maxPrice); // not a query DSL

    List<TicketPrice> namedFindTicketsByPricingCategoryName(@Param("name") String name); // feasible due to the @NamedQuery in Entity TicketPrice

    List<TicketPrice> nativeFindTicketsByCategoryWithWorkshop(@Param("name") String name);
//    @Query(
//            "SELECT tp FROM TicketPrice tp WHERE tp.basePrice< :maxprice" +
//                    "AND tp.ticketType.includesWorkshop = TRUE"
//    )
//    List<TicketPrice> getTicketsUnderPriceWithWorkshops(@Param("maxprice") BigDecimal maxPrice);
}
