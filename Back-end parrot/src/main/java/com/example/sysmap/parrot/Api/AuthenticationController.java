package com.example.sysmap.parrot.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sysmap.parrot.Application.Dto.AuthenticateRequestReponse.AuthenticateReponse;
import com.example.sysmap.parrot.Application.Dto.AuthenticateRequestReponse.AuthenticateRequest;
import com.example.sysmap.parrot.Application.Interfaces.IAuthenticationService;

@RestController
@RequestMapping("api/v1/authentication") 
public class AuthenticationController {
    
    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping()
    public ResponseEntity<AuthenticateReponse> authenticate(@RequestBody AuthenticateRequest  request){
        try {
            return ResponseEntity.ok().body(authenticationService.authenticate(request));
     
        } catch (Exception e) {
            return new ResponseEntity (e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
              

    }


}
