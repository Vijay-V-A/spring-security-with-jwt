package com.vijay.spring_security_jwt.service;

import com.vijay.spring_security_jwt.entity.Users;
import com.vijay.spring_security_jwt.exception.UserNotFoundException;
import com.vijay.spring_security_jwt.repos.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    public Users getOneUsers(String id) throws UserNotFoundException  {

        Optional<Users> user = userRepo.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("User not found: "+ id);

        return user.get();
    }

    public Users updateUsers(String id, @Valid Users data) {
        Users user = getOneUsers(id);
        user.setUserName(data.getUserName());
        user.setPassword(encoder.encode(data.getPassword()));

        return userRepo.save(user);
    }

    public String deleteUsers(String id) {
        Users user = getOneUsers(id);
        userRepo.deleteById(user.getId());

        return "User deleted successfully";
    }
}
