package com.domo.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

/**
 * KafkaConsumer is a configuration class that defines a Spring Cloud Stream Consumer function.
 * <p>
 * This consumer listens for incoming {@link Employee} messages from a Kafka topic configured via application properties,
 * and processes each received message.
 * </p>
 *
 * <p>
 * The consumer function is bound to the input destination using the Spring Cloud Stream bindings
 * (e.g., {@code processEmployeeData-in-0}) and runs automatically upon message arrival.
 * </p>
 *
 * @author YourName
 */
@Configuration
public class KafkaConsumer {

    /**
     * Defines a Consumer bean that processes {@link Employee} messages.
     * <p>
     * When a message is received from the bound Kafka topic, this consumer logs the employee's name and ID.
     * </p>
     *
     * @return a {@link Consumer} that takes an {@link Employee} object and prints its details
     */
    @Bean
    public Consumer<Employee> processEmployeeData() {
        return employee -> {
            System.out.println("Received : " + employee.name + " - " + employee.id); // Debug output
        };
    }
}
