package com.library.security.secure.jwt;

import java.util.List;

public interface IJwtService {

    String generateToken(String email, List<String> roles);

}
