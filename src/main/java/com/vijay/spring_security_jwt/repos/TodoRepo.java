package com.vijay.spring_security_jwt.repos;

import com.vijay.spring_security_jwt.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepo extends JpaRepository<Todo, String> {
}
