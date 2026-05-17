package com.springboot.expensetracker.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ExpenseDto (
        Long id,
        BigDecimal amount,
        LocalDateTime createdAt,
        CategoryDto categoryDto
){
}
