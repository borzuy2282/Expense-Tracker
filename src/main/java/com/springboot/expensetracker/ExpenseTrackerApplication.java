package com.springboot.expensetracker;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Expense Tracker REST API documentation.",
                description = "Expense Tracker REST API documentation. Implemented using SpringBoot.",
                version = "v1.0",
                contact = @Contact(
                        name = "borzuy2282",
                        email = "tim.barzashvili@icloud.com",
                        url = "https://github.com/borzuy2282"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Expense Tracker REST API. Documentation for Developers",
                url = "https://github.com/borzuy2282/Expense-Tracker"
        )
)
@SpringBootApplication
public class ExpenseTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApplication.class, args);
    }

}
