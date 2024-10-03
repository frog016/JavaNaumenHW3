package Database;

import Cinema.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MovieRepository implements CrudRepository<Movie, Long> {
    private final List<Movie> movieDatabase;

    @Autowired
    public MovieRepository(List<Movie> movieConfig) {
        this.movieDatabase = movieConfig;
    }

    @Override
    public void create(Movie entity) {
        movieDatabase.add(entity);
    }

    @Override
    public Movie read(Long id) {
        Optional<Movie> result = movieDatabase.stream()
                .filter(movie -> Objects.equals(movie.getId(), id))
                .findFirst();

        return result.orElse(null);

    }

    @Override
    public void update(Movie entity) {
        Movie targetMovie = read(entity.getId());
        if (targetMovie == null) {
            return;
        }

        targetMovie.setId(entity.getId());
        targetMovie.setGenre(entity.getGenre());
        targetMovie.setDuration(entity.getDuration());
        targetMovie.setDescription(entity.getDescription());
    }

    @Override
    public void delete(Long id) {
        movieDatabase.removeIf(movie -> Objects.equals(movie.getId(), id));
    }
}
