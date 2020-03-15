package com.gallery.controller;

import com.gallery.exeption.GalleryNameAlreadyTakenException;
import com.gallery.exeption.NoPhotoUploadedException;
import com.gallery.model.Gallery;
import com.gallery.model.Image;
import com.gallery.model.User;
import com.gallery.service.GalleryService;
import com.gallery.service.ImageService;
import com.gallery.service.UserService;
import com.gallery.utils.Utils;
import lombok.extern.slf4j.Slf4j;
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

    private final GalleryService galleryService;

    private final UserService userService;

    private final ImageService imageService;

    @Autowired
    public GalleryController(GalleryService galleryService, UserService userService, ImageService imageService) {
        this.galleryService = galleryService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping("/createGalleryPanel")
    public String createGalleryPanel(Model model){

        List<User> clientList = userService.getAllClients();

        model.addAttribute("clientList", clientList);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "create_gallery_panel";
    }
    @GetMapping("/createGallery")
    public String createGallery(Model model, @RequestParam("galleryName") String galleryName,
                                @RequestParam("createdGalleryId") Long createdGalleryId,
                                @RequestParam("clientId") Long clientId)
                                throws GalleryNameAlreadyTakenException {

        if(null == createdGalleryId)
            createdGalleryId= galleryService.createGallery(galleryName, clientId);



        model.addAttribute("createdGalleryId", createdGalleryId);
        model.addAttribute("clientId", clientId);
        model.addAttribute("galleryName", galleryName);
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "create_gallery";
    }

    @PostMapping("/upload/db")
    public String uploadToDB(Model model, @RequestParam("file") MultipartFile file,
                             @RequestParam("createdGalleryId") Long createdGalleryId,
                             @RequestParam("galleryName") String galleryName,
                             @RequestParam("clientId") Long clientId)
                                throws NoPhotoUploadedException{

        if(file.isEmpty()) throw new NoPhotoUploadedException();

        Image doc = new Image();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        doc.setImgName(fileName);
        try {
            doc.setFile(file.getBytes());
            doc.setGallery(galleryService.getGalleryById(createdGalleryId));
        } catch (IOException e) {

        }
        imageService.save(doc);


        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "redirect:/createGallery?galleryName="+galleryName+"&clientId="+clientId+"&createdGalleryId="+createdGalleryId;
    }


}
