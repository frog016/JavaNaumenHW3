package org.example.JavaNaumenHW3.Transaction;

import org.example.JavaNaumenHW3.Entity.Ticket;
import org.example.JavaNaumenHW3.Repository.TicketRepository;
import org.example.JavaNaumenHW3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public UserServiceImplementation(TicketRepository ticketRepository, UserRepository userRepository, PlatformTransactionManager transactionManager) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteUser(Long id) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionAttribute());
        try {
            List<Ticket> tickets = ticketRepository.findByUser(id);
            ticketRepository.deleteAll(tickets);

            userRepository.deleteById(id);

            transactionManager.commit(status);
        }
        catch (DataAccessException exception) {
            transactionManager.rollback(status);
            throw exception;
        }
    }
}
