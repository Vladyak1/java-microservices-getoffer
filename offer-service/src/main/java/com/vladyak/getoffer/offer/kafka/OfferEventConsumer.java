package com.vladyak.getoffer.offer.kafka;

import com.vladyak.getoffer.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OfferEventConsumer {

    private final OfferService offerService;

    @KafkaListener(topics = "${kafka.topic.calculation-completed}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(CalculationCompletedEvent event) {
        log.info("Received calculation result for candidateId={}", event.candidateId());
        offerService.createIfNotExists(event);
    }
}
