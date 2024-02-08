package com.example.companyemployee.repository;
import com.example.companyemployee.entity.Category;
import com.example.companyemployee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {


}
