package com.example.companyemployee.repository;
import com.example.companyemployee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

   Optional<User>findByEmail(String email);

   }


