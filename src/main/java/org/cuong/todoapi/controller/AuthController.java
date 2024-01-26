package org.cuong.todoapi.controller;

import org.cuong.todoapi.dto.LoginDto;
import org.cuong.todoapi.dto.RegisterDto;
import org.cuong.todoapi.dto.JwtAuthToken;
import org.cuong.todoapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String result = authService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthToken> login(@RequestBody LoginDto loginDto) {
        JwtAuthToken jwtAuthToken = authService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body(jwtAuthToken);
    }
}
