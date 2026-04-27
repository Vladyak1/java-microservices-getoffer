package com.vladyak.getoffer.candidate.kafka;

import java.math.BigDecimal;

public record CandidateCreatedEvent(
        Long candidateId,
        String name,
        String level,
        int yearsOfExperience,
        BigDecimal currentSalary
) {}
