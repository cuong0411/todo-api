package org.cuong.todoapi.service;

import org.cuong.todoapi.dto.JwtAuthToken;
import org.cuong.todoapi.dto.LoginDto;
import org.cuong.todoapi.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    JwtAuthToken login(LoginDto loginDto);
}
