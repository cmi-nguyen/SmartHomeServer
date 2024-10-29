package com.example.demo.controllers;

import com.example.demo.entities.Category;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired private CategoryService categoryService;
    // Read
    @GetMapping("/category")
    public List<Category> fetchCategoryList(){
        return categoryService.fetchCategoryList();
    }
    // Save
    @PostMapping("/category")
    public Category saveCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }
    // Update
    @PutMapping("/category/{id}")
    public Category updateCategory(@RequestBody Category category, @PathVariable("id") int categoryID){
        return categoryService.updateCategory(category,categoryID);
    }

    // Delete
    @DeleteMapping("/category/{id}")
    public String deleteCategory(@PathVariable("id") int categoryID){
        categoryService.deleteCategory(categoryID);
        return "Deleted";
    }
}
