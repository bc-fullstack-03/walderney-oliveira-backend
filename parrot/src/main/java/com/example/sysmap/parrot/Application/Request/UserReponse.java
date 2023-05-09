package com.example.sysmap.parrot.Application.Request;

import java.util.List;
import java.util.UUID;

import com.example.sysmap.parrot.Damon.Entities.User;

import lombok.Data;

@Data
public class UserReponse {
    public UUID id;
    public String name;
    public String email;
    public String password;
    //public Profile profile;
    
    
    public UserReponse(UUID id, String name, String email, String password){//, Profile profile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
       // this.profile = profile;
    }


    public UserReponse(User user) {
        this.id=user.getId();
        this.name=user.getName();
        this.email=user.getEmail();
        this.password=user.getPassword();
    }
    
}
