package com.springboot.expensetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(
        description = "Expense DTO to transfer data between client and server"
)
public record ExpenseDto (
        Long id,
        @Schema(description = "Amount of the expense")
        BigDecimal amount,
        @Schema(description = "Time when expense was created")
        LocalDateTime createdAt,
        @Schema(description = "Category of the expense")
        CategoryDto category
){
}
