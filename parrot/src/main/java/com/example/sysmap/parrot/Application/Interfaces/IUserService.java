package com.example.sysmap.parrot.Application.Interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.sysmap.parrot.Application.Dto.UserRequestReponse.UserReponse;
import com.example.sysmap.parrot.Application.Dto.UserRequestReponse.UserRequest;
import com.example.sysmap.parrot.Damon.Entities.User;

public  interface IUserService {
    public String createUser(UserRequest request); 
    public UserReponse findByEmail(String email);
    public String deleteById(String id);
    public List<UserReponse> findAll();
    public UserReponse updateUser(String id ,UserRequest request);
    public User getUserEmail(String email);
    public User getUserById(UUID id);
    
}