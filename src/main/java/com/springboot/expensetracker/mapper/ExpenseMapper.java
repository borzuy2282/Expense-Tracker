package com.springboot.expensetracker.mapper;

import com.springboot.expensetracker.dto.ExpenseDto;
import com.springboot.expensetracker.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    ExpenseDto toDto(Expense expense);
    Expense toEntity(ExpenseDto expenseDto);
}
