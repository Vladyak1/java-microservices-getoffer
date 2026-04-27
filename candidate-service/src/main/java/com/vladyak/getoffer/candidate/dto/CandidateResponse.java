package com.vladyak.getoffer.candidate.dto;

import com.vladyak.getoffer.candidate.model.Candidate;
import com.vladyak.getoffer.candidate.model.Level;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CandidateResponse(
        Long id,
        String name,
        Level level,
        int yearsOfExperience,
        BigDecimal currentSalary,
        LocalDateTime createdAt
) {
    public static CandidateResponse from(Candidate candidate) {
        return new CandidateResponse(
                candidate.getId(),
                candidate.getName(),
                candidate.getLevel(),
                candidate.getYearsOfExperience(),
                candidate.getCurrentSalary(),
                candidate.getCreatedAt()
        );
    }
}
