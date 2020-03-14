package com.gallery.utils;

import com.gallery.model.User;
import com.gallery.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public static User getUser(UserService userService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentlyLoggedUser = userService.getUserByLogin(currentPrincipalName);
        return currentlyLoggedUser;
    }
}
