package com.vladyak.getoffer.calculation.kafka;

import com.vladyak.getoffer.calculation.service.CalculationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class CandidateEventConsumer {

    private final CalculationService calculationService;
    private final CalculationEventProducer producer;
    private final StringRedisTemplate redisTemplate;

    @Value("${redis.calculation.ttl-hours}")
    private long ttlHours;

    @KafkaListener(topics = "${kafka.topic.candidate-created}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CandidateCreatedEvent event) {
        String key = "calc:" + event.candidateId();

        if (Boolean.TRUE.equals(redisTemplate.hasKey(key))) {
            log.info("Candidate {} already processed, skipping", event.candidateId());
            return;
        }

        log.info("Processing candidate id={}", event.candidateId());
        CalculationCompletedEvent result = calculationService.calculate(event);

        redisTemplate.opsForValue().set(key, "processed", ttlHours, TimeUnit.HOURS);
        producer.send(result);
    }
}
