package com.gallery.service;

import com.gallery.model.Role;
import com.gallery.model.User;
import com.gallery.repository.RoleRepository;
import com.gallery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void saveUser(User user) { userRepository.save(user); }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllClients() {
        Role clientRole = roleRepository.findByName("ROLE_CLIENT");
        return userRepository.findAllByRoles(clientRole);
    }
}
