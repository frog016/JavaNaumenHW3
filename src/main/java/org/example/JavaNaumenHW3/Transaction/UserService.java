package org.example.JavaNaumenHW3.Transaction;

import org.example.JavaNaumenHW3.Repository.TicketRepository;
import org.example.JavaNaumenHW3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

public interface UserService {
    void deleteUser(Long id);
}

