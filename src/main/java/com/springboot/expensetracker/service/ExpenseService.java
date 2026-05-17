package com.springboot.expensetracker.service;

import com.springboot.expensetracker.dto.ExpenseDto;
import com.springboot.expensetracker.entity.Category;
import com.springboot.expensetracker.entity.Expense;
import com.springboot.expensetracker.mapper.ExpenseMapper;
import com.springboot.expensetracker.repository.CategoryRepository;
import com.springboot.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public ExpenseService(ExpenseMapper expenseMapper, ExpenseRepository expenseRepository, CategoryRepository categoryRepository) {
        this.expenseMapper = expenseMapper;
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public ExpenseDto createExpense(ExpenseDto expenseDto){
        Category category = categoryRepository.findByName(expenseDto.category().name()).orElse(null);
        Expense expense = expenseMapper.toEntity(expenseDto);
        if(category == null){
            category = new Category();
            category.setName(expenseDto.category().name());
            Category updatedCategory = categoryRepository.save(category);
            expense.setCategory(updatedCategory);
        }else{
            expense.setCategory(category);
        }
        Expense savedExpense = expenseRepository.save(expense);
        return expenseMapper.toDto(savedExpense);
    }
}
