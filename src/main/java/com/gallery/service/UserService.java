package com.gallery.service;

import com.gallery.model.User;
import com.gallery.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;


    public void saveUser(User user) {
        userRepository.save(user); }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
