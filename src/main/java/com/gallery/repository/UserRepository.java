package com.gallery.repository;

import com.gallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByLogin(String login);
    Optional<User> findByLogin(String login);

    /*@Query(
            value ="SELECT COUNT (USER_ID) FROM USER_ROLE WHERE USER_ID = (:USER_ID) AND ROLE_ID = 2")
        // "SELECT * FROM USER_ROLE WHERE USER_ID = (:USER_ID) AND ROLE_ID = 2"
    int isUserAPhotographer(@Param("USER_ID") Long userId);*/
  /*  @Query(
            value =
                    "insert into USER_ROLE values (:USER_ID  , :ROLE_ID)",
            nativeQuery = true)
    void insertUSERTOROLE(@Param("USER_ID") Long USER_ID, @Param("ROLE_ID") Long ROLE_ID);*/
}