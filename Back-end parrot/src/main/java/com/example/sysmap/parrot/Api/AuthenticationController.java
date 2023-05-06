package com.example.sysmap.parrot.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sysmap.parrot.Application.Interfaces.IAuthenticationService;
import com.example.sysmap.parrot.Application.Request.AuthenticateReponse;
import com.example.sysmap.parrot.Application.Request.AuthenticateRequest;

@RestController
@RequestMapping("api/vi/authentication") 
public class AuthenticationController {
    
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping()
    public ResponseEntity<AuthenticateReponse> authenticate(@RequestBody AuthenticateRequest  request){

        return ResponseEntity.ok().body(authenticationService.authenticate(request));
       

    }


}
