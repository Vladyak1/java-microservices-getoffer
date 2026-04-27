package com.vladyak.getoffer.candidate.exception;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(Long id) {
        super("Candidate not found: " + id);
    }
}
