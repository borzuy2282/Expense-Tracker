package com.springboot.expensetracker.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(
        description = "Error details to transfer error response between client and server"
)
public record ErrorDetails(
        @Schema(description = "Error occurred time")
        LocalDateTime timestamp,
        @Schema(description = "Error message")
        String message,
        @Schema(description = "Error URL")
        String details,
        @Schema(description = "Error code")
        String errorCode
) {
}
