package org.example.JavaNaumenHW3.Services;

import org.example.JavaNaumenHW3.Entity.User;
import org.example.JavaNaumenHW3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String fullName, String email, String phoneNumber, String password) {
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);

        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByFullName(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateFullName(Long id, String fullName) {
        User user = userRepository.findById(id).get();
        user.setFullName(fullName);

        userRepository.save(user);
    }
}