package org.example.JavaNaumenHW3.Controllers;

import org.example.JavaNaumenHW3.Entity.Movie;
import org.example.JavaNaumenHW3.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data/view")
public class RepositoryDataWebController {
    private final MovieRepository movieRepository;

    @Autowired
    public RepositoryDataWebController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movie/list")
    public String movieListView(Model model) {
        Iterable<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);

        return "movieList";
    }
}
