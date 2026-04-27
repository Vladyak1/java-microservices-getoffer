package com.vladyak.getoffer.candidate.service;

import com.vladyak.getoffer.candidate.dto.CandidateRequest;
import com.vladyak.getoffer.candidate.dto.CandidateResponse;
import com.vladyak.getoffer.candidate.exception.CandidateNotFoundException;
import com.vladyak.getoffer.candidate.kafka.CandidateCreatedEvent;
import com.vladyak.getoffer.candidate.kafka.CandidateEventProducer;
import com.vladyak.getoffer.candidate.model.Candidate;
import com.vladyak.getoffer.candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateEventProducer eventProducer;

    @Transactional
    public CandidateResponse create(CandidateRequest request) {
        Candidate candidate = Candidate.builder()
                .name(request.name())
                .level(request.level())
                .yearsOfExperience(request.yearsOfExperience())
                .currentSalary(request.currentSalary())
                .build();

        candidate = candidateRepository.save(candidate);

        eventProducer.send(new CandidateCreatedEvent(
                candidate.getId(),
                candidate.getName(),
                candidate.getLevel().name(),
                candidate.getYearsOfExperience(),
                candidate.getCurrentSalary()
        ));

        log.info("Candidate created id={}", candidate.getId());
        return CandidateResponse.from(candidate);
    }

    public CandidateResponse findById(Long id) {
        return candidateRepository.findById(id)
                .map(CandidateResponse::from)
                .orElseThrow(() -> new CandidateNotFoundException(id));
    }
}
