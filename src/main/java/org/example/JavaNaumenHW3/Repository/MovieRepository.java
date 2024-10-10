package org.example.JavaNaumenHW3.Repository;

import org.example.JavaNaumenHW3.Entity.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findByGenreAndTitle(String genre, String title);
}
