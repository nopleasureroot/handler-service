package com.microservices.handler.module;

import com.microservices.handler.model.kafka.DataConsumedEvent;
import com.microservices.handler.module.lamoda.LamodaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BaseHandler implements Handler {
    private final LamodaHandler lamodaHandler;

    @Override
    @KafkaListener(topics = "${spring.kafka.consumer-default-topic}", groupId = "${spring.kafka.consumer.group-id}",
        containerFactory = "kafkaMessageContainerFactory")
    public void handle(@Payload DataConsumedEvent event) {
       switch (1) {
           default -> lamodaHandler.handle(event);
       }
    }
}
