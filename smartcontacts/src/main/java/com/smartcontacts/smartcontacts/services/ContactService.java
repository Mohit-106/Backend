package com.smartcontacts.smartcontacts.services;

import java.util.List;

import com.smartcontacts.smartcontacts.entities.Contact;
import com.smartcontacts.smartcontacts.entities.User;

public interface ContactService {

    //Save contacts
    Contact save(Contact contact);

    //Update
    Contact update(Contact contact);

    //Get Contacts
    List<Contact> getAll();

    //Get by id
    Contact getByID(String id);

    //Search Contact
    List<Contact> search(String name, String email, String phoneNumber);

    // Delete 
    void delete(String id);

    // Get by userID
    List<Contact> getByUserID(String userId);

    List<Contact> getByUser(User user);

}
