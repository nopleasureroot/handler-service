package com.microservices.handler.model.kafka;

import lombok.Builder;

import java.util.UUID;

public record DataProducedEvent(
    UUID uuid,
    Product product
) {

    @Builder
    public DataProducedEvent {
    }

    public record Product(
        String name,
        String imageUrl,
        String link,
        Long price
    ) {
        @Builder
        public Product {
        }
    }
}
