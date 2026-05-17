package com.springboot.expensetracker.mapper;

import com.springboot.expensetracker.CategoryDto;
import com.springboot.expensetracker.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
}
