package org.example.JavaNaumenHW3.Repository;

import org.example.JavaNaumenHW3.Entity.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "movies")
public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByGenreAndTitle(String genre, String title);
}
