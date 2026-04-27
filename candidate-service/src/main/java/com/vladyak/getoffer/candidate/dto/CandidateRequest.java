package com.vladyak.getoffer.candidate.dto;

import com.vladyak.getoffer.candidate.model.Level;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CandidateRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Level is required")
        Level level,

        @Min(value = 0, message = "Years of experience must be non-negative")
        int yearsOfExperience,

        @NotNull(message = "Current salary is required")
        @Positive(message = "Salary must be positive")
        BigDecimal currentSalary
) {}
