package com.vladyak.getoffer.offer.service;

import com.vladyak.getoffer.offer.kafka.CalculationCompletedEvent;
import com.vladyak.getoffer.offer.model.Offer;
import com.vladyak.getoffer.offer.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferService {

    private final OfferRepository offerRepository;

    public void createIfNotExists(CalculationCompletedEvent event) {
        if (offerRepository.existsByCandidateId(event.candidateId())) {
            log.info("Offer for candidate {} already exists, skipping", event.candidateId());
            return;
        }

        Offer offer = Offer.builder()
                .candidateId(event.candidateId())
                .candidateName(event.candidateName())
                .level(event.level())
                .levelCoefficient(event.levelCoefficient())
                .experienceBonus(event.experienceBonus())
                .recommendedSalary(event.recommendedSalary())
                .createdAt(LocalDateTime.now())
                .build();

        offerRepository.save(offer);
        log.info("Offer saved for candidate id={}", event.candidateId());
    }

    public Optional<Offer> findByCandidateId(Long candidateId) {
        return offerRepository.findByCandidateId(candidateId);
    }

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }
}
