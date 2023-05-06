package com.example.sysmap.parrot.Application.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sysmap.parrot.Application.Interfaces.IAuthenticationService;
import com.example.sysmap.parrot.Application.Interfaces.IUserService;
import com.example.sysmap.parrot.Application.Request.AuthenticateReponse;
import com.example.sysmap.parrot.Application.Request.AuthenticateRequest;
import com.example.sysmap.parrot.Application.Security.IJwtService;

@Service
public class AuthenticantonService implements IAuthenticationService {
    
    @Autowired
    private IUserService userService;

    @Autowired
    private IJwtService jwtService;

    @Override
    public AuthenticateReponse authenticate(AuthenticateRequest request){
        var user = userService.getUserEmail(request.email);

        if(user == null){
            return null;
        }

        if(!user.getPassword().equals(request.password)){
            return null;
        }

        var token  = jwtService.generateToken(user.getId());

        var reponse = new AuthenticateReponse();
        reponse.setUserID(user.getId());
        reponse.setToken(token);

        return reponse;
    }
}
