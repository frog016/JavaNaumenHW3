package org.example.JavaNaumenHW3.CriteriaQuery;

import org.example.JavaNaumenHW3.Entity.Movie;

import java.util.List;

public interface MovieRepositoryCustom {
    List<Movie> findByGenreAndTitle(String genre, String title);
}

