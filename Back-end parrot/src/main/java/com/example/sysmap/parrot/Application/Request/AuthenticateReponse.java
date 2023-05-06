package com.example.sysmap.parrot.Application.Request;

import java.util.UUID;

import lombok.Data;

@Data
public class AuthenticateReponse {
    public UUID userID;
    public String token;
}
