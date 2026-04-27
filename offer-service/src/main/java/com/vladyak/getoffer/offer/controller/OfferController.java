package com.vladyak.getoffer.offer.controller;

import com.vladyak.getoffer.offer.dto.OfferResponse;
import com.vladyak.getoffer.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @GetMapping("/{candidateId}")
    public ResponseEntity<OfferResponse> getOffer(@PathVariable Long candidateId) {
        return offerService.findByCandidateId(candidateId)
                .map(OfferResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<OfferResponse> getAllOffers() {
        return offerService.findAll().stream()
                .map(OfferResponse::from)
                .toList();
    }
}
