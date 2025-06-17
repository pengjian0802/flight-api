package com.pj.flightapi.service.impl;

import com.pj.flightapi.dto.UserDto;
import com.pj.flightapi.entity.User;
import com.pj.flightapi.repository.UserRepository;
import com.pj.flightapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserDetailByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (null == user) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities("USER")
        );
    }

    @Override
    public UserDto saveUser(User user) {
        User userEntity = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setCountry(userEntity.getCountry());
        userDto.setPhone(userEntity.getPhone());
        return userDto;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User userEntity = userRepository.findByEmail(email).orElse(null);
        if (null == userEntity) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setCountry(userEntity.getCountry());
        userDto.setPhone(userEntity.getPhone());
        return userDto;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }
}