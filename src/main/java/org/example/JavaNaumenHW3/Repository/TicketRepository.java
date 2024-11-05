package org.example.JavaNaumenHW3.Repository;

import org.example.JavaNaumenHW3.Entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "tickets")
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    @Query("FROM Ticket WHERE user.id = :id")
    List<Ticket> findByUser(Long id);
}
