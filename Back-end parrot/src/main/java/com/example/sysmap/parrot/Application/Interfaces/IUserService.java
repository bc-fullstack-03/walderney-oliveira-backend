package com.example.sysmap.parrot.Application.Interfaces;

import java.util.List;
import java.util.Optional;

import com.example.sysmap.parrot.Application.Request.UserReponse;
import com.example.sysmap.parrot.Application.Request.UserRequest;

public  interface IUserService {
    public String createUser(UserRequest request); 
    public UserReponse findByEmail(String email);
    public String deleteById(String id);
    public List<UserReponse> findAll();
    public UserReponse updateUser(String id ,UserRequest request);
    
}
