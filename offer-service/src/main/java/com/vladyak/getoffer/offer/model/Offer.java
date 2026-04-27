package com.vladyak.getoffer.offer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "offers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id
    private String id;

    @Indexed(unique = true)
    private Long candidateId;

    private String candidateName;
    private String level;
    private double levelCoefficient;
    private double experienceBonus;
    private BigDecimal recommendedSalary;
    private LocalDateTime createdAt;
}
