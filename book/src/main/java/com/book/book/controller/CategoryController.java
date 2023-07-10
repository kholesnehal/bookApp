package com.book.book.controller;

import com.book.book.entity.Author;
import com.book.book.entity.Category;
import com.book.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> findAllCategory()
    {
        List<Category> categoryList= categoryService.findAllCategories();
        return categoryList;
    }

    @GetMapping("/category/{id}")
    public Category findCategoryById(@PathVariable("id") Long id)
    {
     Category category=categoryService.findCategoryById(id);
     return category;
    }

    @PostMapping("/addCategory")
    public String addCategory(@RequestBody Category category)
    {
        categoryService.addCategory(category);
        return "category added successfully";
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody Category updatedCategory){

        Category existingCategory = categoryService.findCategoryById(categoryId);

        if (existingCategory == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the author's properties
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setCategoryId(updatedCategory.getCategoryId());


        // Save the updated author
     categoryService.updateCategory(existingCategory);

        return ResponseEntity.ok("category updated successfully");
    }

    @DeleteMapping("/remove-category/{id}")
    public String deleteAuthor(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "category delete successfully!";
    }
}
