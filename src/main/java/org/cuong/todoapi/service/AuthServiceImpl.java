package org.cuong.todoapi.service;

import java.util.HashSet;
import java.util.Set;

import org.cuong.todoapi.dto.LoginDto;
import org.cuong.todoapi.dto.RegisterDto;
import org.cuong.todoapi.entity.Role;
import org.cuong.todoapi.entity.User;
import org.cuong.todoapi.exception.RoleNotFoundByName;
import org.cuong.todoapi.exception.TodoApiException;
import org.cuong.todoapi.repository.RoleRepository;
import org.cuong.todoapi.repository.UserRepository;
import org.cuong.todoapi.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        var u = new User();
        u.setName(registerDto.getName());
        u.setUsername(registerDto.getUsername());
        u.setEmail(registerDto.getEmail());
        u.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER")
                            .orElseThrow(() -> new RoleNotFoundByName("Role user not found!"));
        roles.add(role);
        u.setRoles(roles);

        userRepository.save(u);
        return "User registered successfully!";
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication  = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
            ));
        } catch (AuthenticationException e) {
            throw new TodoApiException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
    
}
