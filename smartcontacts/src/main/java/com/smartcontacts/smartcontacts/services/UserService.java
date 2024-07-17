package com.smartcontacts.smartcontacts.services;
import java.util.Optional;
import com.smartcontacts.smartcontacts.entities.User;

import java.util.List;


public interface UserService {
    User saveUser(User user);

    // User getUserById(String Id); optional can be use to handle some edge cases
    Optional<User> getUserById(String ID);
    Optional<User> updateUser(User user);

    void deleteUser(String ID);

    boolean isUserExist(String userID);

    boolean isUserExistByEmail(String email);

    List<User> getAllUsers();

    // add more
    
}
