package org.example.JavaNaumenHW3.Repository;

import org.example.JavaNaumenHW3.Entity.Hall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "halls")
public interface HallRepository extends CrudRepository<Hall, Long> {
}
