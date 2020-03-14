package com.gallery.controller;

import com.gallery.model.Gallery;
import com.gallery.model.Role;
import com.gallery.model.User;
import com.gallery.repository.RoleRepository;
import com.gallery.repository.UserRepository;
import com.gallery.service.GalleryService;
import com.gallery.service.ImageService;
import com.gallery.service.UserService;
import com.gallery.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        for (Role role: currentlyLoggedUserRolesList) {
            if(role.getName().equalsIgnoreCase("ROLE_PHOTOGRAPHER"))
                return "main_photographer";
            if(role.getName().equalsIgnoreCase("ROLE_CLIENT"))
                return "main_client";
        }
        return null;
    }



}
