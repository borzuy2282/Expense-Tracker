package com.springboot.expensetracker.mapper;

import com.springboot.expensetracker.dto.ExpenseDto;
import com.springboot.expensetracker.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    @Mapping(source = "category", target = "categoryDto")
    ExpenseDto toDto(Expense expense);
    @Mapping(source = "categoryDto", target = "category")
    Expense toEntity(ExpenseDto expenseDto);
}
