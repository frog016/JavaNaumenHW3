package org.example.JavaNaumenHW3.Database;

import org.example.JavaNaumenHW3.Cinema.MovieOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MovieOldRepository implements CrudRepository<MovieOld, Long> {
    private final List<MovieOld> movieOldDatabase;

    @Autowired
    public MovieOldRepository(List<MovieOld> movieOldConfig) {
        this.movieOldDatabase = movieOldConfig;
    }

    @Override
    public void create(MovieOld entity) {
        movieOldDatabase.add(entity);
    }

    @Override
    public MovieOld read(Long id) {
        Optional<MovieOld> result = movieOldDatabase.stream()
                .filter(movie -> Objects.equals(movie.getId(), id))
                .findFirst();

        return result.orElse(null);

    }

    @Override
    public void update(MovieOld entity) {
        MovieOld targetMovieOld = read(entity.getId());
        if (targetMovieOld == null) {
            return;
        }

        targetMovieOld.setId(entity.getId());
        targetMovieOld.setGenre(entity.getGenre());
        targetMovieOld.setDuration(entity.getDuration());
        targetMovieOld.setDescription(entity.getDescription());
    }

    @Override
    public void delete(Long id) {
        movieOldDatabase.removeIf(movie -> Objects.equals(movie.getId(), id));
    }
}
