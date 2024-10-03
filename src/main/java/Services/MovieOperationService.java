package Services;

import Cinema.Movie;
import Database.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieOperationService implements MovieService {
    private final MovieRepository repository;

    @Autowired
    public MovieOperationService(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Movie createMovie(Long id, int genre, float duration, String description) {
        Movie movie = new Movie(id, genre, duration, description);
        repository.create(movie);

        return movie;
    }

    @Override
    public Movie findById(Long id) {
        return repository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.delete(id);
    }

    @Override
    public void updateDescription(Long id, String description) {
        Movie oldMovie = repository.read(id);
        Movie newMovie = new Movie(id, oldMovie.getGenre(), oldMovie.getDuration(), description);

        repository.update(newMovie);
    }
}
