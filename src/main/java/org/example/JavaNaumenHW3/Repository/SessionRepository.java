package org.example.JavaNaumenHW3.Repository;

import org.example.JavaNaumenHW3.Entity.Hall;
import org.example.JavaNaumenHW3.Entity.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "sessions")
public interface SessionRepository extends CrudRepository<Session, Long> {
    @Query("FROM Session WHERE hall.display = :hallDisplay")
    List<Session> findByHall(String hallDisplay);
}
