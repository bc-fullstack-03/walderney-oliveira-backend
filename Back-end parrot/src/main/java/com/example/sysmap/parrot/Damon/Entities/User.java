package com.example.sysmap.parrot.Damon.Entities;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class User {
    
    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String imageUrl;
    private Boolean photo= false;
    private String username;

    public User(String name, String email,String username) {
        this.id=UUID.randomUUID();
        this.email=email;
        this.username=username;
    }

}
