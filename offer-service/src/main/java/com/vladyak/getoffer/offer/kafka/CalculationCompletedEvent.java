package com.vladyak.getoffer.offer.kafka;

import java.math.BigDecimal;

public record CalculationCompletedEvent(
        Long candidateId,
        String candidateName,
        String level,
        double levelCoefficient,
        double experienceBonus,
        BigDecimal recommendedSalary
) {}
