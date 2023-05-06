package com.example.sysmap.parrot.Application.Request;

import lombok.Data;

@Data
public class AuthenticateRequest {
    
    public String email;
    public String password;
}
