package com.example.sysmap.parrot.Damon.Entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Profile  {
    
    @Id
    private UUID id;
    private String name;
    private String imageUrl;

    public Profile(Profile profile){
        this.id = UUID.randomUUID();
        this.name= getName();
        this.imageUrl = getImageUrl();
    }
}

    
