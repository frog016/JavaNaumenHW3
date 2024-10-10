package org.example.JavaNaumenHW3;

import org.example.JavaNaumenHW3.Entity.Ticket;
import org.example.JavaNaumenHW3.Entity.User;
import org.example.JavaNaumenHW3.Repository.TicketRepository;
import org.example.JavaNaumenHW3.Repository.UserRepository;
import org.example.JavaNaumenHW3.Transaction.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class DeleteUserTransactionTest {
    private final UserService userService;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public DeleteUserTransactionTest(UserService userService, UserRepository userRepository, TicketRepository ticketRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Test
    void testDeleteUserWithTickets() {
        User user = new User();
        user.setId((long)Math.round((float) (Math.random() * 1000)));
        user.setFullName(UUID.randomUUID().toString());
        user.setEmail(UUID.randomUUID().toString());
        user.setPhoneNumber(UUID.randomUUID().toString());
        userRepository.save(user);

        Ticket ticket1 = new Ticket();
        ticket1.setId((long)Math.round((float) (Math.random() * 1000)));
        ticket1.setUser(user);
        ticket1.setPrice(Math.round((float) (Math.random() * 1000)));
        ticket1.setSeatNumber(Math.round((float) (Math.random() * 1000)));
        ticketRepository.save(ticket1);

        Ticket ticket2 = new Ticket();
        ticket2.setId((long)Math.round((float) (Math.random() * 1000)));
        ticket2.setUser(user);
        ticket2.setPrice(Math.round((float) (Math.random() * 1000)));
        ticket2.setSeatNumber(Math.round((float) (Math.random() * 1000)));
        ticketRepository.save(ticket2);

        userService.deleteUser(user.getId());

        Optional<User> notFoundUser = userRepository.findById(user.getId());
        Assertions.assertTrue(notFoundUser.isEmpty());

        Optional<Ticket> notFoundTicket1 = ticketRepository.findById(ticket1.getId());
        Assertions.assertTrue(notFoundTicket1.isEmpty());

        Optional<Ticket> notFoundTicket2 = ticketRepository.findById(ticket2.getId());
        Assertions.assertTrue(notFoundTicket2.isEmpty());
    }
}
