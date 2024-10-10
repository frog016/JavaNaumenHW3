package org.example.JavaNaumenHW3.Services;

import org.example.JavaNaumenHW3.Cinema.MovieOld;
import org.example.JavaNaumenHW3.Database.MovieOldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieOperationService implements MovieService {
    private final MovieOldRepository repository;

    @Autowired
    public MovieOperationService(MovieOldRepository repository) {
        this.repository = repository;
    }

    @Override
    public MovieOld createMovie(Long id, int genre, float duration, String description) {
        MovieOld movieOld = new MovieOld(id, genre, duration, description);
        repository.create(movieOld);

        return movieOld;
    }

    @Override
    public MovieOld findById(Long id) {
        return repository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.delete(id);
    }

    @Override
    public void updateDescription(Long id, String description) {
        MovieOld oldMovieOld = repository.read(id);
        MovieOld newMovieOld = new MovieOld(id, oldMovieOld.getGenre(), oldMovieOld.getDuration(), description);

        repository.update(newMovieOld);
    }
}
