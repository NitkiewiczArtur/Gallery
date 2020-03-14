package com.gallery.repository;

import com.gallery.model.Role;
import com.gallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoleRepository extends JpaRepository <Role, Long> {
    List<Role> findAllByUsers(User user);
    Role findByName(String name);
}
