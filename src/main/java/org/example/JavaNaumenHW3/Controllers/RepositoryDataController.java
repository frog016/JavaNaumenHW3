package org.example.JavaNaumenHW3.Controllers;

import org.example.JavaNaumenHW3.Entity.Movie;
import org.example.JavaNaumenHW3.Entity.Session;
import org.example.JavaNaumenHW3.Entity.Ticket;
import org.example.JavaNaumenHW3.Entity.User;
import org.example.JavaNaumenHW3.Repository.MovieRepository;
import org.example.JavaNaumenHW3.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/data")
public class RepositoryDataController {
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public RepositoryDataController(MovieRepository movieRepository, TicketRepository ticketRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/movie/findByGenreAndTitle")
    public List<Movie> findByGenreAndTitle(@RequestParam String genre, @RequestParam String title) {
        return movieRepository.findByGenreAndTitle(genre, title);
    }

    @GetMapping("/ticket/findByUser")
    public List<Ticket> findByUser(@RequestParam Long userId) {
        return ticketRepository.findByUser(userId);
    }

    @PostMapping("/movie/add")
    public void addMovie(@RequestParam String genre, @RequestParam String title, @RequestParam float duration) {
        var movie = new Movie();
        movie.setGenre(genre);
        movie.setTitle(title);
        movie.setDuration(duration);

        movieRepository.save(movie);
    }

    @PostMapping("/ticket/add")
    public void addTicket(@RequestParam int price, @RequestParam int seatNumber) {
        var ticket = new Ticket();
        var user = new User();
        var session = new Session();

        ticket.setUser(user);
        ticket.setSession(session);
        ticket.setPrice(price);
        ticket.setSeatNumber(seatNumber);

        ticketRepository.save(ticket);
    }
}
