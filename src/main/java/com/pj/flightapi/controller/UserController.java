package com.pj.flightapi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
//
//    @GetMapping("/me")
//    @PreAuthorize("hasRole('USER')")
//    public String getCurrentUser() {
//        return "This is the current user's profile";
//    }
//
//    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String getAdminDashboard() {
//        return "This is the admin dashboard";
//    }
}    