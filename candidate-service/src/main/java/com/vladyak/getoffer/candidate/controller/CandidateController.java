package com.vladyak.getoffer.candidate.controller;

import com.vladyak.getoffer.candidate.dto.CandidateRequest;
import com.vladyak.getoffer.candidate.dto.CandidateResponse;
import com.vladyak.getoffer.candidate.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateResponse create(@Valid @RequestBody CandidateRequest request) {
        return candidateService.create(request);
    }

    @GetMapping("/{id}")
    public CandidateResponse getById(@PathVariable Long id) {
        return candidateService.findById(id);
    }
}
