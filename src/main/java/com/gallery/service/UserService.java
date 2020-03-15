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

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }


    public List<User> getAllClients() {
        Role clientRole = roleService.findByName("ROLE_CLIENT");
        return userRepository.findAllByRoles(clientRole);
    }
}
