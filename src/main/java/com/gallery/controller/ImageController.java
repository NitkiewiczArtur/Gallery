package com.gallery.controller;


import com.gallery.model.Gallery;
import com.gallery.model.Image;
import com.gallery.repository.ImageRepository;
import com.gallery.service.GalleryService;
import com.gallery.service.ImageService;
import com.gallery.service.UserService;
import com.gallery.utils.Utils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ImageController {

    private final GalleryService galleryService;

    private final UserService userService;

    private final ImageService imageService;

    @Autowired
    public ImageController(GalleryService galleryService, UserService userService, ImageService imageService) {
        this.galleryService = galleryService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/showGallery")
    public String showGallery(Model model, @RequestParam Long galleryId) {

        Gallery galleryToShow = galleryService.getGalleryById(galleryId);
        String galleryName = galleryToShow.getGalleryName();
        List<Image> photosFromGallery = galleryToShow.getImages();
        model.addAttribute("galleryName", galleryName);
        model.addAttribute("photosToShow", photosFromGallery);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));

        return "show_gallery";
    }

    @GetMapping("/product/image/{id}")
    public void showProductImage(@PathVariable Long id,
                                 HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");

        Image image = imageService.getImageById(id);

        InputStream is = new ByteArrayInputStream(image.getFile());
        IOUtils.copy(is, response.getOutputStream());
    }

}