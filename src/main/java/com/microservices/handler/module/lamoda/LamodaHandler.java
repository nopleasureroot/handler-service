package com.microservices.handler.module.lamoda;

import com.fasterxml.jackson.databind.JsonNode;
import com.microservices.handler.model.kafka.DataConsumedEvent;
import com.microservices.handler.model.kafka.DataProducedEvent;
import com.microservices.handler.module.Handler;
import com.microservices.handler.module.producer.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.microservices.handler.util.Constant.EMPTY_STRING;
import static com.microservices.handler.util.Constant.LAMODA_BASE_LOGO_URL;
import static com.microservices.handler.util.Constant.LAMODA_BASE_PRODUCT_URL;
import static com.microservices.handler.util.Constant.LAMODA_DEFAULT_PRODUCT_NAME;
import static com.microservices.handler.util.Constant.LAMODA_IMAGE_BASE_URL;
import static com.microservices.handler.util.Constant.LAMODA_IMAGE_FIELD_VALUE;
import static com.microservices.handler.util.Constant.LAMODA_PRICE_FIELD_VALUE;
import static com.microservices.handler.util.Constant.LAMODA_PRODUCT_NAME_FIELD_VALUE;
import static com.microservices.handler.util.Constant.LAMODA_SKU_FIELD_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class LamodaHandler implements Handler {
    private final Producer producer;

    @Override
    public void handle(DataConsumedEvent event) {
        JsonNode response = event.responseBody();
        producer.execute(DataProducedEvent.builder()
            .product(DataProducedEvent.Product.builder()
                .price(getPrice(response))
                .name(getProductName(response))
                .imageUrl(getImageUrl(response))
                .link(getProductUrl(response))
                .build())
            .uuid(event.uuid())
            .build());
    }

    private String getProductName(JsonNode response) {
        return Optional.ofNullable(response.get(LAMODA_PRODUCT_NAME_FIELD_VALUE).asText())
            .orElse(LAMODA_DEFAULT_PRODUCT_NAME);
    }

    private Long getPrice(JsonNode response) {
        return Optional.of(response.get(LAMODA_PRICE_FIELD_VALUE).asLong())
            .filter(price -> price > 0).orElse(Long.MAX_VALUE);
    }

    private String getImageUrl(JsonNode response) {
        return LAMODA_IMAGE_BASE_URL
            .concat(Optional.ofNullable(
                    response.get(LAMODA_IMAGE_FIELD_VALUE).get(0).asText())
                .orElse(EMPTY_STRING));
    }

    private String getProductUrl(JsonNode response) {
        return Optional.ofNullable(response.get(LAMODA_SKU_FIELD_VALUE).asText())
            .filter(path -> !path.isEmpty()).map(LAMODA_BASE_PRODUCT_URL::concat)
            .orElse(LAMODA_BASE_LOGO_URL);
    }
}
