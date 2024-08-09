package com.smartcontacts.smartcontacts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartcontacts.smartcontacts.entities.Contact;
import com.smartcontacts.smartcontacts.entities.User;
import com.smartcontacts.smartcontacts.forms.ContactForm;
import com.smartcontacts.smartcontacts.helper.Helper;
import com.smartcontacts.smartcontacts.helper.Message;
import com.smartcontacts.smartcontacts.helper.MessageType;
import com.smartcontacts.smartcontacts.services.ContactService;
import com.smartcontacts.smartcontacts.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
    Authentication authentication, HttpSession session) {

        if (result.hasErrors()) {
            session.setAttribute("message", Message.builder()
            .content("Please correct following errors")
            .type(MessageType.red)
            .build());
            return "user/add_contact";
        }

        String username = Helper.getEmailofLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        // convert form to contact
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedinLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        contactService.save(contact);

        Message message= Message.builder().content("Contact added successfully").type(MessageType.green).build();
        session.setAttribute("message", message);
        
        return "redirect:/user/contact/add";
    }

    // add contact page handler
    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

}
