package com.smartcontacts.smartcontacts.helper;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    // We need email so that i can get the user info
    public static String getEmailofLoggedInUser(Authentication authentication){

        if(authentication instanceof OAuth2AuthenticationToken){
            // if user if loggedin by google
            System.out.println("Logged in with google");
            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username = oauth2User.getAttribute("email").toString();
            return username;
        }else{
            // if user is logged in by in app Sigup
            System.out.println("Logged in with Uname Password");
            return authentication.getName();
        }
    }
}


