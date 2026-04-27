package com.vladyak.getoffer.calculation.service;

import com.vladyak.getoffer.calculation.kafka.CalculationCompletedEvent;
import com.vladyak.getoffer.calculation.kafka.CandidateCreatedEvent;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class CalculationService {

    private static final Map<String, Double> LEVEL_COEFFICIENTS = Map.of(
            "JUNIOR", 1.0,
            "MIDDLE", 1.3,
            "SENIOR", 1.7
    );
    private static final double MARKET_ADJUSTMENT = 0.10;
    private static final double EXPERIENCE_RATE = 0.03;
    private static final double MAX_EXPERIENCE_BONUS = 0.30;

    public CalculationCompletedEvent calculate(CandidateCreatedEvent event) {
        double levelCoefficient = LEVEL_COEFFICIENTS.getOrDefault(event.level(), 1.0);
        double experienceBonus = Math.min(event.yearsOfExperience() * EXPERIENCE_RATE, MAX_EXPERIENCE_BONUS);

        BigDecimal recommendedSalary = event.currentSalary()
                .multiply(BigDecimal.valueOf(levelCoefficient))
                .multiply(BigDecimal.valueOf(1 + experienceBonus))
                .multiply(BigDecimal.valueOf(1 + MARKET_ADJUSTMENT))
                .setScale(2, RoundingMode.HALF_UP);

        return new CalculationCompletedEvent(
                event.candidateId(),
                event.name(),
                event.level(),
                levelCoefficient,
                experienceBonus,
                recommendedSalary
        );
    }
}
