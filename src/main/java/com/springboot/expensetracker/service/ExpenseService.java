package com.springboot.expensetracker.service;

import com.springboot.expensetracker.dto.ExpenseDto;
import com.springboot.expensetracker.entity.Expense;
import com.springboot.expensetracker.mapper.ExpenseMapper;
import com.springboot.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    private final ExpenseMapper expenseMapper;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseMapper expenseMapper, ExpenseRepository expenseRepository) {
        this.expenseMapper = expenseMapper;
        this.expenseRepository = expenseRepository;
    }

    public ExpenseDto createExpense(ExpenseDto expenseDto){
        Expense expense = expenseMapper.toEntity(expenseDto);
        Expense savedExpense = expenseRepository.save(expense);
        return expenseMapper.toDto(savedExpense);
    }
}
