package com.domo.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining Kafka topics.
 * <p>
 * This configuration ensures that the required Kafka topic is created at application startup,
 * if it does not already exist.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Creates a new Kafka topic named {@code "My-new-topic-intellij"} with 3 partitions and a replication factor of 1.
     * <p>
     * This bean is automatically picked up by Spring Kafka to manage topic creation using KafkaAdmin.
     *
     * @return a {@link NewTopic} instance representing the topic configuration
     */
    @Bean
    public NewTopic createMyTopic() {
        return new NewTopic("My-new-topic-intellij", 3, (short) 1);
    }
}
