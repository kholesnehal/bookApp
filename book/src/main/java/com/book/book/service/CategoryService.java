package com.book.book.service;

import com.book.book.entity.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAllCategories();

    public Category findCategoryById(Long id);

    public Category addCategory(Category category);

    public Category updateCategory(Category category);

    public void deleteCategory(Long id);
}
