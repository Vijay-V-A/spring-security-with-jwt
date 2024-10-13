package com.vijay.spring_security_jwt.repos;


import com.vijay.spring_security_jwt.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, String> {

    Users findByUserName(String username);
}
