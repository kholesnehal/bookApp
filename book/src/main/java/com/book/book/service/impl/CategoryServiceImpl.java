package com.book.book.service.impl;

import com.book.book.entity.Category;
import com.book.book.exception.NotFoundException;
import com.book.book.repository.CategoryRepository;
import com.book.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<Category> findAllCategories() {
       List<Category>categoryList=categoryRepository.findAll();
       return categoryList;
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));

    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category) ;
    }

    @Override
    public Category updateCategory(Category category) {
    Category updatedCategory=categoryRepository.save(category);
    return updatedCategory;
    }

    @Override
    public void deleteCategory(Long id) {
        final Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Category not found  with ID %d", id)));

        categoryRepository.deleteById(category.getCategoryId());
    }
}
