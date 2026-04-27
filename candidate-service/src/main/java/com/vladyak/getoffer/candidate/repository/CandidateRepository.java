package com.vladyak.getoffer.candidate.repository;

import com.vladyak.getoffer.candidate.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
