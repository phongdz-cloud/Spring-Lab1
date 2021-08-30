package com.example.test9.Service;

import com.example.test9.Model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    void delete(Long id);

}
