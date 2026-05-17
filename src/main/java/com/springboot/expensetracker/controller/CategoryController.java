package com.springboot.expensetracker.controller;

import com.springboot.expensetracker.dto.CategoryDto;
import com.springboot.expensetracker.exception.DuplicateCategoryNameException;
import com.springboot.expensetracker.exception.ErrorDetails;
import com.springboot.expensetracker.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
    }


    @ExceptionHandler(DuplicateCategoryNameException.class)
    public ResponseEntity<ErrorDetails> duplicateCategoryNameExceptionHandler(DuplicateCategoryNameException ex,
                                                                              WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false),
                "DUPLICATE_CATEGORY_NAME"
        );
        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }
}
