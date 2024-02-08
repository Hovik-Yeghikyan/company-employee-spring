package com.example.companyemployee.service;

import com.example.companyemployee.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    List<Category> findAll();
}
