package com.vijay.spring_security_jwt.service;


import com.vijay.spring_security_jwt.entity.UserPrinciple;
import com.vijay.spring_security_jwt.entity.Users;
import com.vijay.spring_security_jwt.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepo.findByUserName(username);

        if(user.getId() == null) throw new UsernameNotFoundException("User Not found");

        return new UserPrinciple(user);
    }
}
