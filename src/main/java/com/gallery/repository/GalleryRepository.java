package com.gallery.repository;

import com.gallery.model.Gallery;
import com.gallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GalleryRepository extends JpaRepository < Gallery, Long > {
    List<Gallery> getGalleryByUsers(User user);
    Gallery getByGalleryName(String galleryName);
    Gallery getById(Long id);
    @Transactional
    @Modifying
    @Query(
            value =
                    "insert into USER_GALLERY values (:USER_ID  , :GALLERY_ID)",
            nativeQuery = true)
    void insertGalleryToUser(@Param("USER_ID") Long USER_ID, @Param("GALLERY_ID") Long GALLERY_ID);
}
