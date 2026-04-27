package com.vladyak.getoffer.offer.dto;

import com.vladyak.getoffer.offer.model.Offer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OfferResponse(
        String id,
        Long candidateId,
        String candidateName,
        String level,
        double levelCoefficient,
        double experienceBonus,
        BigDecimal recommendedSalary,
        LocalDateTime createdAt
) {
    public static OfferResponse from(Offer offer) {
        return new OfferResponse(
                offer.getId(),
                offer.getCandidateId(),
                offer.getCandidateName(),
                offer.getLevel(),
                offer.getLevelCoefficient(),
                offer.getExperienceBonus(),
                offer.getRecommendedSalary(),
                offer.getCreatedAt()
        );
    }
}
