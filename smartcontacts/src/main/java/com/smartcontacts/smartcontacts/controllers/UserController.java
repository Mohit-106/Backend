package com.smartcontacts.smartcontacts.controllers;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/user")
public class UserController {


    //User Dashboard
    @RequestMapping(value = "/dashboard", method=RequestMethod.GET)
    public String userDashboard() {
        return "/user/dashboard";
    }

    //User Profile Page 
    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String userProfile(Model model, Authentication authentication) {
        return "user/profile";
    }

}