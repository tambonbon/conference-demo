package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.TicketPriceJpaRepository;
import com.pluralsight.conferencedemo.repositories.TicketTypeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class TicketTypeTest {
    @Autowired
    private TicketTypeJpaRepository jpaRepository;

    @Test
    public void testJpaTrue() throws Exception {
        List<TicketType> types = jpaRepository.findByIncludesWorkshopTrue();
        assertTrue(types.size() > 0);
    }
}
