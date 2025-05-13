package com.domo.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

/**
 * KafkaProducer is a configuration class that defines a Spring Cloud Stream Supplier function.
 * <p>
 * This supplier periodically generates an {@link Employee} object and publishes it to the configured Kafka topic.
 * The polling interval is defined in the application's configuration (e.g., every 5 seconds).
 * </p>
 *
 * <p>
 * This is useful for scenarios where you want to continuously emit data at a fixed interval.
 * </p>
 *
 * @author YourName
 */
@Configuration
public class KafkaProducer {

    /**
     * Defines a Supplier bean that produces an {@link Employee} object with a fixed name ("Kevin").
     * <p>
     * Spring Cloud Stream will automatically poll this method at a fixed delay (configured in YAML),
     * and send the returned Employee to the Kafka topic bound to {@code sendEmployeeDetails-out-0}.
     * </p>
     *
     * @return a {@link Supplier} that returns a new {@link Employee} instance
     */
    @Bean
    public Supplier<Employee> sendEmployeeDetails() {
        return () -> {
            Employee employee = new Employee("Kevin");
            System.out.println("Sending : " + employee.name); // Only for debug
            return employee;
        };
    }
}
