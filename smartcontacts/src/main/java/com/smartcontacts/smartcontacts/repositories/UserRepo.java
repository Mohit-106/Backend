package com.smartcontacts.smartcontacts.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcontacts.smartcontacts.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{
    //Extra methods db relatedoperations
    //Custom query methods 
    //custom finder methods
    
    // These method are custum but will implemented by jpa by its own
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

} 