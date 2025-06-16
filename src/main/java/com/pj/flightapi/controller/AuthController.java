package com.pj.flightapi.controller;

import com.pj.flightapi.common.Result;
import com.pj.flightapi.common.ResultCode;
import com.pj.flightapi.dto.AuthRequest;
import com.pj.flightapi.dto.AuthResponse;
import com.pj.flightapi.dto.RegisterRequest;
import com.pj.flightapi.entity.User;
import com.pj.flightapi.repository.UserRepository;
import com.pj.flightapi.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public Result<AuthResponse> register(@RequestBody RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getFirstName() + user.getLastName());
        return Result.success(AuthResponse.builder().token(token).build());
    }

    @PostMapping("/login")
    public Result<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(request.getEmail());
            System.out.println(token);
            User user = userRepository.findByEmail(request.getEmail()).orElse(null);
            return Result.success(AuthResponse.builder().token(token).user(user).build());
        } else {
            return Result.error(ResultCode.USER_NOT_FOUND);
        }
    }
}