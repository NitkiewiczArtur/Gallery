package com.gallery.service;

import com.gallery.exeption.GalleryNameAlreadyTakenException;
import com.gallery.exeption.GalleryNameIsNullException;
import com.gallery.model.Gallery;
import com.gallery.model.User;
import com.gallery.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {


    private final GalleryRepository galleryRepository;
    private final UserService userService;

    @Autowired
    public GalleryService(UserService userService, GalleryRepository galleryRepository) {
        this.userService = userService;
        this.galleryRepository = galleryRepository;
    }

    public Long getGalleryIdFromName(String galleryName) {
        return galleryRepository.getByGalleryName(galleryName).getId();
    }

    public Gallery getGalleryById(Long id) {
        return galleryRepository.getById(id);
    }

    public List<Gallery> getGalleriesByUserId(User user) {
        return galleryRepository.getGalleryByUsers(user);
    }

    public Long createGallery(String galleryName, Long clientId) throws GalleryNameAlreadyTakenException {
        Gallery gallery = new Gallery();
        if (galleryRepository.existsGalleryByGalleryName(galleryName)) throw new GalleryNameAlreadyTakenException();
        if (galleryName.isEmpty()) throw new GalleryNameIsNullException();
        gallery.setGalleryName(galleryName);
        Long galleryId = galleryRepository.save(gallery).getId();
        galleryRepository.insertGalleryToUser(getCurrentlyloggedUser().getId(), galleryId);
        galleryRepository.insertGalleryToUser(clientId, galleryId);
        return galleryId;
    }

    private User getCurrentlyloggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userService.getUserByLogin(currentPrincipalName);
    }

}
