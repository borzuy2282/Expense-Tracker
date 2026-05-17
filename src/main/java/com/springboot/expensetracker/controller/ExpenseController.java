package com.springboot.expensetracker.controller;

import com.springboot.expensetracker.dto.ExpenseDto;
import com.springboot.expensetracker.exception.ErrorDetails;
import com.springboot.expensetracker.exception.ExpenseNotFoundException;
import com.springboot.expensetracker.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("create")
    public ResponseEntity<ExpenseDto> createExpense(@RequestBody ExpenseDto expenseDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(expenseService.createExpense(expenseDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable Long id){
        return ResponseEntity.ok(expenseService.getExpense(id));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(){
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }


    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorDetails> expenseNotFoundExceptionHandler(ExpenseNotFoundException ex,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false),
                "EXPENSE_NOT_FOUND"
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorDetails);
    }
}
