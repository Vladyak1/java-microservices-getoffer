package com.vladyak.getoffer.candidate.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CandidateEventProducer {

    private final KafkaTemplate<String, CandidateCreatedEvent> kafkaTemplate;

    @Value("${kafka.topic.candidate-created}")
    private String topic;

    public void send(CandidateCreatedEvent event) {
        kafkaTemplate.send(topic, String.valueOf(event.candidateId()), event);
        log.info("Published CandidateCreatedEvent for candidateId={}", event.candidateId());
    }
}
