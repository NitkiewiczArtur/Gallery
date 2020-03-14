package com.gallery.controller;

import com.gallery.model.Gallery;
import com.gallery.model.Image;
import com.gallery.model.Role;
import com.gallery.model.User;
import com.gallery.repository.RoleRepository;
import com.gallery.repository.UserRepository;
import com.gallery.service.GalleryService;
import com.gallery.service.ImageService;
import com.gallery.service.UserService;
import com.gallery.utils.Utils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository ur;
    @Autowired
    ImageService imageService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    GalleryService galleryService;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("/main")
    public String getMainView(Model model) {
        User currentlyLoggedUser = Utils.getUser(userService);
        List<Role> currentlyLoggedUserRolesList = roleRepository.findAllByUsers(currentlyLoggedUser);
        List<Gallery> galleryList = galleryService.getGalleriesByUserId(currentlyLoggedUser);


        model.addAttribute("galleryList" , galleryList);
        model.addAttribute("currentlyLoggedUser", currentlyLoggedUser);

        if(currentlyLoggedUserRolesList.size() > 1)
        return "main_photographer";
        else
            return "main_client";
    }

    @GetMapping("/createGallery")
    public String createGallery(Model model, @RequestParam("galleryName") String galleryName){
        User currentlyLoggedUser = Utils.getUser(userService);
        galleryService.createGallery(galleryName);


        model.addAttribute("currentlyLoggedUser", currentlyLoggedUser);
        return "createGallery";
    }


    @PostMapping("/upload/db")
    public ResponseEntity uploadToDB(@RequestParam("file") MultipartFile file) {
        Image doc = new Image();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        doc.setDocName(fileName);
        try {
            doc.setFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageService.save(doc);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName).path("/db")
                .toUriString();
        return ResponseEntity.ok(fileDownloadUri);
    }

}
