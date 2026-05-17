package com.springboot.expensetracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Category DTO to transfer data between client and server"
)
public record CategoryDto(
        Long id,
        @Schema(description = "Category's name")
        String name
){
}