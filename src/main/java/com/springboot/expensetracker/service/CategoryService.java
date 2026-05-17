package com.springboot.expensetracker.service;

import com.springboot.expensetracker.dto.CategoryDto;
import com.springboot.expensetracker.entity.Category;
import com.springboot.expensetracker.exception.CategoryNotFoundException;
import com.springboot.expensetracker.exception.DuplicateCategoryNameException;
import com.springboot.expensetracker.mapper.CategoryMapper;
import com.springboot.expensetracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public CategoryDto getCategory(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category with id " + id + " was not found!"));
        return categoryMapper.toDto(category);
    }

    public List<CategoryDto> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto){
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Category with id " + id + " was not found!")
        );
        if (categoryRepository.existsByName(categoryDto.name())){
            throw new DuplicateCategoryNameException("Category with this name already exists!");
        }
        category.setName(categoryDto.name());
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
