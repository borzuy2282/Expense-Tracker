package com.springboot.expensetracker.service;

import com.springboot.expensetracker.dto.CategoryDto;
import com.springboot.expensetracker.entity.Category;
import com.springboot.expensetracker.exception.DuplicateCategoryNameException;
import com.springboot.expensetracker.mapper.CategoryMapper;
import com.springboot.expensetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto createCategory(CategoryDto categoryDto){
        Category category = categoryMapper.toEntity(categoryDto);
        if(categoryRepository.existsByName(category.getName())){
            throw new DuplicateCategoryNameException("Category with this name already exists!");
        }
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }
}
