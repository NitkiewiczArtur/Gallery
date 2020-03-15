package com.gallery.controller;

import com.gallery.model.Gallery;
import com.gallery.model.Image;
import com.gallery.model.User;
import com.gallery.repository.ImageRepository;
import com.gallery.service.GalleryService;
import com.gallery.service.ImageService;
import com.gallery.service.UserService;
import com.gallery.utils.Utils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class GalleryController {

    @Autowired
    GalleryService galleryService;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;


    @GetMapping("/createGalleryPanel")
    public String createGalleryPanel(Model model){

        List<User> clientList = userService.getAllClients();

        model.addAttribute("clientList", clientList);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "create_new_gallery";
    }
    @GetMapping("/createGallery")
    public String createGallery(Model model, @RequestParam("galleryName") String galleryName, @RequestParam("clientId") Long clientId){

        galleryService.createGallery(galleryName, clientId);

        model.addAttribute("galleryName", galleryName);
        model.addAttribute("galleryId", galleryService.getGalleryIdFromName(galleryName));
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "create_gallery";
    }

    @PostMapping("/upload/db")
    public String uploadToDB(Model model, @RequestParam("file") MultipartFile file, @RequestParam("galleryId")Long galleryId, @RequestParam("galleryName") String galleryName) {
        Image doc = new Image();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        doc.setImgName(fileName);
        try {
            doc.setFile(file.getBytes());
            doc.setGallery(galleryService.getGalleryById(galleryId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageService.save(doc);

        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "redirect:/createGallery?galleryName="+galleryName;
    }

    @GetMapping("/showGallery")
    public String showGallery(Model model, @RequestParam Long galleryId) {

        Gallery galleryToShow = galleryService.getGalleryById(galleryId);
        List<Image> photosFromGallery = galleryToShow.getImages();

        model.addAttribute("photosToShow", photosFromGallery);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));

        return "show_gallery";
    }
    @GetMapping("/product/image/{id}")
    public void showProductImage(@PathVariable Long id,
                               HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg"); // Or whatever format you wanna use

        Image image = imageService.getImageById(id);

        InputStream is = new ByteArrayInputStream(image.getFile());
        IOUtils.copy(is, response.getOutputStream());
    }

}
