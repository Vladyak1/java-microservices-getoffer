package com.vladyak.getoffer.calculation.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CalculationEventProducer {

    private final KafkaTemplate<String, CalculationCompletedEvent> kafkaTemplate;

    @Value("${kafka.topic.calculation-completed}")
    private String topic;

    public void send(CalculationCompletedEvent event) {
        kafkaTemplate.send(topic, String.valueOf(event.candidateId()), event);
        log.info("Published CalculationCompletedEvent for candidateId={}", event.candidateId());
    }
}
