package org.example.JavaNaumenHW3;

import org.example.JavaNaumenHW3.CriteriaQuery.SessionRepositoryCustom;
import org.example.JavaNaumenHW3.Entity.Hall;
import org.example.JavaNaumenHW3.Entity.Movie;
import org.example.JavaNaumenHW3.Entity.Session;
import org.example.JavaNaumenHW3.Repository.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class SessionTest {
    private final SessionRepository sessionRepository;
    private final SessionRepositoryCustom sessionRepositoryCustom;

    @Autowired
    public SessionTest(SessionRepository sessionRepository, SessionRepositoryCustom sessionRepositoryCustom) {
        this.sessionRepository = sessionRepository;
        this.sessionRepositoryCustom = sessionRepositoryCustom;
    }

    @Test
    void testFindByHallSuccess() {
        Hall hall = getHall();
        Movie movie = getMovie();
        Date dateTime = Date.valueOf(LocalDate.now());
        int availableSeats = Math.round((float) (Math.random() * 10));

        Session session = new Session();
        session.setHall(hall);
        session.setMovie(movie);
        session.setDateTime(dateTime);
        session.setAvailableSeats(availableSeats);
        sessionRepository.save(session);

        Session foundSession = sessionRepository.findByHall(hall.getDisplay()).getFirst();

        Assertions.assertNotNull(foundSession);
        Assertions.assertEquals(session.getId(), foundSession.getId());
        Assertions.assertEquals(session.getHall().getDisplay(), foundSession.getHall().getDisplay());
    }

    @Test
    void testFindByHallNotExistingFail() {

        Hall hall = getHall();
        Movie movie = getMovie();
        Date dateTime = Date.valueOf(LocalDate.now());
        int availableSeats = Math.round((float) (Math.random() * 10));

        Session session = new Session();
        session.setHall(hall);
        session.setMovie(movie);
        session.setDateTime(dateTime);
        session.setAvailableSeats(availableSeats);
        sessionRepository.save(session);

        List<Session> notFoundSession = sessionRepository.findByHall("abracadabra");
        Assertions.assertTrue(notFoundSession.isEmpty());
    }

    @Test
    void testCustomFindByHallNotExistingFail() {
        List<Session> notFoundSession = sessionRepositoryCustom.findByHall("abracadabra");
        Assertions.assertTrue(notFoundSession.isEmpty());
    }

    private static Hall getHall() {
        int totalSeats = Math.round((float) (Math.random() * 1000));
        String display = UUID.randomUUID().toString();
        String soundSystem = UUID.randomUUID().toString();

        Hall hall = new Hall();
        hall.setTotalSeats(totalSeats);
        hall.setDisplay(display);
        hall.setSoundSystem(soundSystem);

        return hall;
    }

    private static Movie getMovie() {
        String title = UUID.randomUUID().toString();
        String genre = UUID.randomUUID().toString();
        float duration = (float) Math.random();

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDuration(duration);

        return movie;
    }
}
