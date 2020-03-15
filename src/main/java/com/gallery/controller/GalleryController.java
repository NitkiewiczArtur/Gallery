package com.gallery.controller;

import com.gallery.model.Gallery;
import com.gallery.model.Image;
import com.gallery.model.User;
import com.gallery.service.GalleryService;
import com.gallery.service.ImageService;
import com.gallery.service.UserService;
import com.gallery.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Controller
public class GalleryController {

    @Autowired
    GalleryService galleryService;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;


    @GetMapping("/createGallery")
    public String createGallery(Model model, @RequestParam("galleryName") String galleryName, @RequestParam("clientId") Long clientId){

        galleryService.createGallery(galleryName, clientId);

        model.addAttribute("galleryId", galleryService.getGalleryIdFromName(galleryName));
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "create_gallery";
    }
    @GetMapping("/createGalleryPanel")
    public String createGalleryPanel(Model model){

        List<User> clientList = userService.getAllClients();

        model.addAttribute("clientList", clientList);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "create_new_gallery";
    }

    @GetMapping("/showGallery")
    public String showGallery(Model model, @RequestParam Long galleryId) {

        Gallery galleryToShow = galleryService.getGalleryById(galleryId);

        model.addAttribute("galleryToShow", galleryToShow);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));

        return "showWorkoutResults";
    }
    @PostMapping("/upload/db")
    public String uploadToDB(Model model, @RequestParam("file") MultipartFile file, @RequestParam("galleryId")Long galleryId ) {
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
        return "create_gallery";
    }
}
