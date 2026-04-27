package com.vladyak.getoffer.offer.repository;

import com.vladyak.getoffer.offer.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OfferRepository extends MongoRepository<Offer, String> {

    Optional<Offer> findByCandidateId(Long candidateId);

    boolean existsByCandidateId(Long candidateId);
}
