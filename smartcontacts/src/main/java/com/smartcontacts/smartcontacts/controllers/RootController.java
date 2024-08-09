package com.smartcontacts.smartcontacts.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.smartcontacts.smartcontacts.entities.User;
import com.smartcontacts.smartcontacts.helper.Helper;
import com.smartcontacts.smartcontacts.services.UserService;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService; 

    @ModelAttribute
    public void addLoggedInUserInformation(Model model,Authentication authentication){
        if(authentication == null){
            return;
        }
        System.out.println("Adding loggedIn User to the model");
        String username = Helper.getEmailofLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        System.out.println(user);
        model.addAttribute("user",user);
    }

}
