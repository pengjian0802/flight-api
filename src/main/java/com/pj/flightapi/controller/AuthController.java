package com.pj.flightapi.controller;

import com.pj.flightapi.common.Result;
import com.pj.flightapi.common.ResultCode;
import com.pj.flightapi.dto.AuthRequest;
import com.pj.flightapi.dto.AuthResponse;
import com.pj.flightapi.dto.RegisterRequest;
import com.pj.flightapi.dto.UserDto;
import com.pj.flightapi.entity.User;
import com.pj.flightapi.repository.UserRepository;
import com.pj.flightapi.service.UserService;
import com.pj.flightapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<AuthResponse> register(@RequestBody RegisterRequest request) {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .country(request.getCountry())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        UserDto userDto = userService.saveUser(user);
        String token = jwtUtil.generateToken(request.getEmail());
        return Result.success(AuthResponse.builder().token(token).user(userDto).build());
    }

    @PostMapping("/login")
    public Result<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        UserDto userDto = userService.findUserByEmail(request.getEmail());
        if (null == userDto) {
            return Result.error(ResultCode.USER_NOT_FOUND);
        }
        Boolean result = userService.validatePassword(userDto.getId(), request.getPassword(), passwordEncoder);
        if (!result) {
            return Result.error(ResultCode.INVALID_PASSWORD);
        }
        String token = jwtUtil.generateToken(request.getEmail());
        return Result.success(AuthResponse.builder().token(token).user(userDto).build());
    }
}