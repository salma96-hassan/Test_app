package com.Test.app.Test_app.ui.controllers;

import com.Test.app.Test_app.security.JwtService;
import com.Test.app.Test_app.security.loginDto;
import com.Test.app.Test_app.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/user")
    public String getUsers() {
        return "Users";
    }

    @GetMapping("/user/sec")
    public String getUserSec() {
        return "UserSec";
    }

    @PostMapping("/authenticate")
    public String Authenticate(@RequestBody loginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if (authenticate.isAuthenticated()) {
           return jwtService.generateToken(userServiceImpl.loadUserByUsername(loginDto.getUsername()));
        }else{
            throw  new UsernameNotFoundException("Invalid Credentials");
        }
    }
}
