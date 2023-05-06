package com.example.sysmap.parrot.Application.Dto.UserRequestReponse;

import com.example.sysmap.parrot.Damon.Entities.Profile;

import lombok.Data;

@Data
public class UserRequest { 
    public String name;
    public String email;
    public String password;
    public Profile profile;
    
}
