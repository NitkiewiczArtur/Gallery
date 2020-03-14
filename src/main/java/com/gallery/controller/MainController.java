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
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        for (Role role: currentlyLoggedUserRolesList) {
            if(role.getName().equalsIgnoreCase("ROLE_PHOTOGRAPHER"))
                return "main_photographer";
            if(role.getName().equalsIgnoreCase("ROLE_CLIENT"))
                return "main_client";
        }
        return null;
    }

    @GetMapping("/createGallery")
    public String createGallery(Model model, @RequestParam("galleryName") String galleryName, @RequestParam("clientId") Long clientId){

        galleryService.createGallery(galleryName, clientId);

        model.addAttribute("galleryId", galleryService.getGalleryIdFromName(galleryName));
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "createGallery";
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
        doc.setDocName(fileName);
        try {
            doc.setFile(file.getBytes());
            doc.setGallery(galleryService.getGalleryById(galleryId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageService.save(doc);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/download/")
                .path(fileName).path("/db")
                .toUriString();
        model.addAttribute("currentlyLoggedUser", Utils.getUser(userService));
        return "createGallery";
    }

}
