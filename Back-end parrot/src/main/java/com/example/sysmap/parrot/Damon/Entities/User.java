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
    private Profile profile;

    public User(String name, String email,Profile profile) {
        this.id=UUID.randomUUID();
        this.email=email;
        this.profile=profile;
    }

}
