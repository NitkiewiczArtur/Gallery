package com.gallery.service;

import com.gallery.model.Image;
import com.gallery.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image getImageById(Long id){ return imageRepository.getOne(id);}
    public void save (Image image){
        imageRepository.save(image);
    }
}
