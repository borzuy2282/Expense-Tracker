package com.springboot.expensetracker.controller;

import com.springboot.expensetracker.dto.ExpenseDto;
import com.springboot.expensetracker.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for Expense",
        description = "CRUD REST APIs for Expense: Create Expense" +
                "Update Expense, Get Expense/s and Delete Expense"
)
@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Operation(
            summary = "Create Expense",
            description = "Used to create Expense into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
    @PostMapping("create")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseService.createExpense(expenseDto));
    }

    @Operation(
            summary = "Get Expense",
            description = "Used to get Expense from database by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @GetMapping("{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable Long id){
        return ResponseEntity.ok(expenseService.getExpense(id));
    }

    @Operation(
            summary = "Get all Expenses",
            description = "Used to get all Expenses from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @Operation(
            summary = "Update Expense",
            description = "Used to update Expense in database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @PutMapping("{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@PathVariable Long id,
                                                    @RequestBody ExpenseDto expenseDto){
        return ResponseEntity.ok(expenseService.updateExpense(id, expenseDto));
    }

    @Operation(
            summary = "Delete Expense",
            description = "Used to delete Expense from database"
    )
    @ApiResponse(
            responseCode = "204",
            description = "HTTP STATUS 204 NO CONTENT"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
