package com.smartcontacts.smartcontacts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartcontacts.smartcontacts.entities.Contact;
import com.smartcontacts.smartcontacts.entities.User;
import com.smartcontacts.smartcontacts.helper.Helper;
import com.smartcontacts.smartcontacts.services.ContactService;
import com.smartcontacts.smartcontacts.services.UserService;

@Controller


@RequestMapping("/user/contacts")
public class ContactsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @RequestMapping()
    public String viewContacts(Model model, Authentication authentication){

        String username = Helper.getEmailofLoggedInUser(authentication);
        
        User user = userService.getUserByEmail(username);

        List<Contact> contacts = contactService.getByUser(user);

        model.addAttribute("contacts", contacts);
        


        return "/user/contacts";
    }
}
