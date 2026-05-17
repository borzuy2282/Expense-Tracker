package com.springboot.expensetracker.controller;

import com.springboot.expensetracker.dto.CategoryDto;
import com.springboot.expensetracker.exception.DuplicateCategoryNameException;
import com.springboot.expensetracker.exception.ErrorDetails;
import com.springboot.expensetracker.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for Category",
        description = "CRUD REST APIs for Category: Create category" +
        "Update Category, Get Category/ies and Delete Categories"
)
@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(
            summary = "Create Category",
            description = "Used to save Category into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
    @PostMapping("create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
    }

    @Operation(
            summary = "Get Category",
            description = "Used to get Category from database by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @Operation(
            summary = "Get Categories",
            description = "Used to get all Categories from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(
            summary = "Update Category",
            description = "Used to update Category in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    @Operation(
            summary = "Delete Category",
            description = "Used to delete Category from database"
    )
    @ApiResponse(
            responseCode = "204",
            description = "HTTP STATUS 204 NO CONTENT"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
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
