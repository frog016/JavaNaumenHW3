package org.example.JavaNaumenHW3.Repository;

import org.example.JavaNaumenHW3.Entity.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
