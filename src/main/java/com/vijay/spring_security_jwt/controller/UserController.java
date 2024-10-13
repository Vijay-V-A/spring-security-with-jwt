package com.vijay.spring_security_jwt.controller;


import com.vijay.spring_security_jwt.entity.Users;
import com.vijay.spring_security_jwt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public ResponseEntity<List<Users>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Users> getOneUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.getOneUsers(id), HttpStatus.OK);
    }

    @PatchMapping("users/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable String id, @Valid @RequestBody Users data) {
        return new ResponseEntity<>(userService.updateUsers(id,data), HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String>  deleteUser(@PathVariable String id) {
        return new ResponseEntity<>(userService.deleteUsers(id), HttpStatus.OK);
    }
}
