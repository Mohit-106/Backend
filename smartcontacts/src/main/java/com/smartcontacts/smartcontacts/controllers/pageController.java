package com.smartcontacts.smartcontacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartcontacts.smartcontacts.entities.User;
import com.smartcontacts.smartcontacts.forms.UserForm;
import com.smartcontacts.smartcontacts.helper.Message;
import com.smartcontacts.smartcontacts.helper.MessageType;
import com.smartcontacts.smartcontacts.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


@Controller
public class pageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(){
        System.out.println("Home page loading....");
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        System.out.println("About page loading....");
        return "about";
    }

    @RequestMapping("/services")
    public String services(){
        System.out.println("Services page loading....");
        return "services";
    }

    @RequestMapping("/contacts")
    public String contacts(){
        System.out.println("contacts page loading....");
        return "contacts";
    }

    @RequestMapping("/login")
    public String login(){
        System.out.println("Services page loading....");
        return "login";
    }
    
    @RequestMapping("/register")
    public String register(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    //Processing register
    @RequestMapping(value = "/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){   
        
        // Validation
        if(rBindingResult.hasErrors()){
            session.setAttribute("message", Message.builder()
            .content("Please correct following errors")
            .type(MessageType.red)
            .build());
            return "register";
        }

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://assets.leetcode.com/users/avatars/avatar_1706981301.png");
        User saveUser = userService.saveUser(user);


        // Message
        Message message= Message.builder().content("You have registered successfully").type(MessageType.green).build();
        session.setAttribute("message", message);

        return "redirect:/register";
        
    }
}
