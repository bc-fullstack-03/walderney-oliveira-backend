package com.example.sysmap.parrot.Application.Interfaces;

import com.example.sysmap.parrot.Application.Request.AuthenticateReponse;
import com.example.sysmap.parrot.Application.Request.AuthenticateRequest;

public interface IAuthenticationService {
    public AuthenticateReponse authenticate(AuthenticateRequest request);
}
