package com.gallery.repository;

import com.gallery.model.Role;
import com.gallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByLogin(String login);
    Optional<User> findByLogin(String login);
    List<User> findAllByRoles(Role role);
}