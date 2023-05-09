package com.example.sysmap.parrot.Application.Interfaces;

import com.example.sysmap.parrot.Application.Dto.AuthenticateRequestReponse.AuthenticateReponse;
import com.example.sysmap.parrot.Application.Dto.AuthenticateRequestReponse.AuthenticateRequest;

public interface IAuthenticationService {
    public AuthenticateReponse authenticate(AuthenticateRequest request) throws Exception;
}
