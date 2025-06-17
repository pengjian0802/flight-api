package com.pj.flightapi.service;

import com.pj.flightapi.dto.UserDto;
import com.pj.flightapi.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDetails loadUserDetailByEmail(String email);

    UserDto saveUser(User user);

    UserDto findUserByEmail(String email);
}
