package com.gallery.service;

import com.gallery.model.Role;
import com.gallery.model.User;
import com.gallery.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    public List<Role> findAllByUsers(User user) {
        return roleRepository.findAllByUsers(user);
    }
}
