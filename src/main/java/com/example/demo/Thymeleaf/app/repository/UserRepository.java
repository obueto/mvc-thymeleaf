package com.example.demo.Thymeleaf.app.repository;

import com.example.demo.Thymeleaf.app.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
