package com.smartcontacts.smartcontacts.services.impl;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartcontacts.smartcontacts.entities.User;
import com.smartcontacts.smartcontacts.repositories.UserRepo;
import com.smartcontacts.smartcontacts.services.UserService;
import com.smartcontacts.smartcontacts.helper.AppConstants;
import com.smartcontacts.smartcontacts.helper.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    // this will save data in the database
    private UserRepo userRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;


    // it will gandle the login tasks
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        //password encoding will be done here
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set user role
        user.setRoleList(List.of(AppConstants.ROLE_USER));

        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String ID) {
        return userRepo.findById(ID);
    }

    @Override
    public Optional<User> updateUser(User user) {
        
        User user2 = userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("Usetr not found"));
        // Update 
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneNumberVerified(user.isPhoneNumberVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());
        // Save user in data base
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);

    }

    @Override
    public void deleteUser(String ID) {
        User user2 = userRepo.findById(ID).orElseThrow(()->new ResourceNotFoundException("Usetr not found"));
        userRepo.delete(user2);
    }

    @Override
    public boolean isUserExist(String userID) {
        User user2 = userRepo.findById(userID).orElse(null);
        return user2!=null ? true:false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user!=null ? true:false;
    }

    @Override
    public List<User> getAllUsers() {
       return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(null);
    }

}
