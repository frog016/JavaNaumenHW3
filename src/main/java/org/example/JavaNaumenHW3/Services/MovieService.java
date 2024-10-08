package org.example.JavaNaumenHW3.Services;

import org.example.JavaNaumenHW3.Cinema.MovieOld;

public interface MovieService {
    MovieOld createMovie(Long id, int genre, float duration, String description);
    MovieOld findById(Long id);
    void deleteById(Long id);
    void updateDescription(Long id, String description);
}

