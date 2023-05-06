package com.example.sysmap.parrot.Application.Security;

import java.util.UUID;

public interface IJwtService {
    public String generateToken(UUID userId);
}
