package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryImpl implements CategoryService{
    @Autowired private CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> fetchCategoryList() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category, int categoryID) {
        Category cateDB = categoryRepository.findById(categoryID).get();
        if (Objects.nonNull(category.getCategoryID())){
            cateDB.setCategoryID(category.getCategoryID());
        }
        if (Objects.nonNull(category.getCategoryName())&&!"".equalsIgnoreCase(category.getCategoryName())){
            cateDB.setCategoryName(category.getCategoryName());
        }
        if (Objects.nonNull(category.getStatus())){
            cateDB.setStatus(category.getStatus());
        }
        return categoryRepository.save(cateDB);
    }

    @Override
    public void deleteCategory(int categoryID) {
        categoryRepository.deleteById(categoryID);
    }
}
