package com.gallery.service;

import com.gallery.model.Image;
import com.gallery.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void save (Image image){
        imageRepository.save(image);
    }
}
