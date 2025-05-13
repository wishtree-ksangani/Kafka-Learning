package com.domo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka consumer component that listens to messages from a Kafka topic.
 * <p>
 * This consumer is part of the consumer group {@code "my-group-1"} and listens to messages on the topic {@code "my-topic-11"}.
 */
@Component
public class KafkaConsumer {

    /**
     * Listens to the Kafka topic {@code "my-topic-11"} and processes incoming {@link Employee} messages.
     * <p>
     * This method is triggered automatically by Spring Kafka when a new message is received.
     *
     * @param employee the {@link Employee} object received from the topic
     */
    @KafkaListener(topics = "my-topic-11", groupId = "my-group-1")
    public void listen1(Employee employee) {
        System.out.println("Listened from 1: " + employee.name + " - " + employee.id);
    }
}
