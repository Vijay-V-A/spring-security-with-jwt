package com.vijay.spring_security_jwt.service;

import com.vijay.spring_security_jwt.config.JWTService;
import com.vijay.spring_security_jwt.entity.TokenResponse;
import com.vijay.spring_security_jwt.entity.Users;
import com.vijay.spring_security_jwt.repos.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;


    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public Users singUp(Users data) {
        data.setPassword(encoder.encode(data.getPassword()));
        System.out.println(data.toString());
        return userRepo.save(data);
    }

    public TokenResponse signIn(Users user) throws Exception {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user);
        else throw new Exception("UserName or Password is incorrect");
    }

    public Users signOut(@Valid Users data) {
        return null;
    }
}
