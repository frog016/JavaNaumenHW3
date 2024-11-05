package org.example.JavaNaumenHW3.Services;

import org.example.JavaNaumenHW3.Entity.User;

public interface UserService {
    User createUser(String fullName, String email, String phoneNumber);
    User findById(Long id);
    void deleteById(Long id);
    void updateFullName(Long id, String fullName);
}

