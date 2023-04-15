package com.microservices.handler.module;

import com.microservices.handler.model.kafka.DataConsumedEvent;

public interface Handler {
    void handle(DataConsumedEvent event);
}
