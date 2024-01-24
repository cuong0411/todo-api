package org.cuong.todoapi.service;

import org.cuong.todoapi.dto.LoginDto;
import org.cuong.todoapi.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
