package com.gallery.service;

import com.gallery.model.Gallery;
import com.gallery.model.Image;
import com.gallery.model.User;
import com.gallery.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

    @Autowired
    GalleryRepository galleryRepository;
    @Autowired
    UserService userService;

    public Long getGalleryIdFromName(String galleryName){
        return galleryRepository.getByGalleryName(galleryName).getId();
    }
    public void createGallery(String galleryName, Long clientId){
        Gallery gallery = new Gallery();
        gallery.setGalleryName(galleryName);
        Long galleryId = galleryRepository.save(gallery).getId();
        galleryRepository.insertGalleryToUser(getCurrentlyloggedUser().getId(), galleryId);
        galleryRepository.insertGalleryToUser(clientId, galleryId);
    }


    public Gallery getGalleryById(Long id){
        return galleryRepository.getById(id);

    }

    public void addImageToGallery(Image image, Long galleryId){

    }

    public List<Gallery> getGalleriesByUserId(User user){
        return galleryRepository.getGalleryByUsers(user);
    }
    private User getCurrentlyloggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userService.getUserByLogin(currentPrincipalName);
    }

}
