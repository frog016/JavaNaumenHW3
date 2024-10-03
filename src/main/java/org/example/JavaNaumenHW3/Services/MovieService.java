package org.example.JavaNaumenHW3.Services;

import org.example.JavaNaumenHW3.Cinema.Movie;

public interface MovieService {
    Movie createMovie(Long id, int genre, float duration, String description);
    Movie findById(Long id);
    void deleteById(Long id);
    void updateDescription(Long id, String description);
}

