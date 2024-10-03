package Services;

import Cinema.Movie;
import Database.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface MovieService {
    Movie createMovie(Long id, int genre, float duration, String description);
    Movie findById(Long id);
    void deleteById(Long id);
    void updateDescription(Long id, String description);
}

