package com.smartcontacts.smartcontacts.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcontacts.smartcontacts.entities.Contact;
import com.smartcontacts.smartcontacts.helper.ResourceNotFoundException;
import com.smartcontacts.smartcontacts.repositories.ContactRepo;
import com.smartcontacts.smartcontacts.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService{


    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {

        String contactid = UUID.randomUUID().toString();
        contact.setId(contactid);
        return contactRepo.save(contact);
        
    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getByID(String id) {
        return contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact Not Found"));
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public void delete(String id) {
        var contact = contactRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Contact Not Found"));
        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> getByUserID(String userId) {
        return contactRepo.findByUserId(userId);
    }

}
