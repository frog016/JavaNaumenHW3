package org.example.JavaNaumenHW3;

import org.example.JavaNaumenHW3.CriteriaQuery.MovieRepositoryCustom;
import org.example.JavaNaumenHW3.Entity.Movie;
import org.example.JavaNaumenHW3.Repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
public class MovieTest {
    private final MovieRepository movieRepository;
    private final MovieRepositoryCustom movieRepositoryCustom;

    @Autowired
    public MovieTest(MovieRepository movieRepository, MovieRepositoryCustom movieRepositoryCustom) {
        this.movieRepository = movieRepository;
        this.movieRepositoryCustom = movieRepositoryCustom;
    }

    @Test
    void testFindByGenreAndTitleSuccess() {
        String title = UUID.randomUUID().toString();
        String genre = UUID.randomUUID().toString();
        float duration = (float) Math.random();

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDuration(duration);

        movieRepository.save(movie);

        Movie foundMovie = movieRepository.findByGenreAndTitle(genre, title).getFirst();

        Assertions.assertNotNull(foundMovie);
        Assertions.assertEquals(movie.getId(), foundMovie.getId());
        Assertions.assertEquals(movie.getTitle(), foundMovie.getTitle());
        Assertions.assertEquals(movie.getGenre(), foundMovie.getGenre());
    }

    @Test
    void testFindByGenreAndTitleNotExistingFail() {
        String title = UUID.randomUUID().toString();
        String genre = UUID.randomUUID().toString();
        float duration = (float) Math.random();

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDuration(duration);

        movieRepository.save(movie);

        List<Movie> notFoundMovie = movieRepository.findByGenreAndTitle("abracadabra", "not title");

        Assertions.assertTrue(notFoundMovie.isEmpty());
    }

    @Test
    void testCustomFindByGenreAndTitleNotExistingFail() {
        List<Movie> notFoundMovie = movieRepositoryCustom.findByGenreAndTitle("abracadabra", "not title");
        Assertions.assertTrue(notFoundMovie.isEmpty());
    }
}
