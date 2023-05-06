package com.example.sysmap.parrot.Application.Dto.AuthenticateRequestReponse;

import lombok.Data;

@Data
public class AuthenticateRequest {
    
    public String email;
    public String password;
}
