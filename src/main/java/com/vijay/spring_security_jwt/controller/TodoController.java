package com.vijay.spring_security_jwt.controller;

import com.vijay.spring_security_jwt.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin("*")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("todo")
    public ResponseEntity<String> getAllTodos() {
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }


}
