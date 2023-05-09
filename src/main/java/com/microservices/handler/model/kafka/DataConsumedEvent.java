package com.microservices.handler.model.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.microservices.handler.model.ShopName;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public record DataConsumedEvent(
    @NotNull UUID uuid,
    @NotNull JsonNode responseBody
    ) {
}
