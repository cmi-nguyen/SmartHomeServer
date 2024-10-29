package com.example.demo.services;

import com.example.demo.entities.Category;

import java.util.List;

public interface CategoryService {
    // Save Op
    Category saveCategory(Category category);

    // Read op

    List<Category> fetchCategoryList();

    // Update op

    Category updateCategory(Category category, int categoryID);

    // Delete op
    void deleteCategory(int categoryID);
}
