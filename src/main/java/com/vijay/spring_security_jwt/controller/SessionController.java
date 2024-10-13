package com.vijay.spring_security_jwt.controller;

import com.vijay.spring_security_jwt.entity.TokenResponse;
import com.vijay.spring_security_jwt.entity.Users;
import com.vijay.spring_security_jwt.service.SessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping("signUp")
    public ResponseEntity<Users> singUp(@Valid @RequestBody Users data) {
        System.out.println(data.toString());
        return new ResponseEntity<>(sessionService.singUp(data), HttpStatus.CREATED);
    }

    @PostMapping("signIn")
    public ResponseEntity<TokenResponse> singIn(@Valid @RequestBody Users user) throws Exception {
        return new ResponseEntity<>(sessionService.signIn(user), HttpStatus.OK);
    }

    @DeleteMapping("signOut")
    public ResponseEntity<Users> postUserDetail(@Valid @RequestBody Users data) {
        return new ResponseEntity<>(sessionService.signOut(data), HttpStatus.OK);
    }
}
