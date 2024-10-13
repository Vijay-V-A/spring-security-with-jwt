package com.vijay.spring_security_jwt.service;


import com.vijay.spring_security_jwt.repos.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {


    @Autowired
    private TodoRepo todoRepo;


    public String getAllTodos() {
        return  "i am from String";
    }

}
