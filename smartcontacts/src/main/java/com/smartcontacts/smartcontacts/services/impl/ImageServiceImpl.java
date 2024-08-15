package com.smartcontacts.smartcontacts.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smartcontacts.smartcontacts.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

    @Override
    public String uploadImage(MultipartFile contactImage) {        
        return "";
    }

}
