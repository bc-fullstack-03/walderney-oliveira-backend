package com.example.sysmap.parrot.Damon.Entities;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Profile {
    
    
    private String username;
    private String imageUrl;
    private boolean photo= false;

    public Profile(Profile profile){
        this.username= profile.getUsername();
        this.imageUrl = profile.getImageUrl();
    }

  
    
}
    
