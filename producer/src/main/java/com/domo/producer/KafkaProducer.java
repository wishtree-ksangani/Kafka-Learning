package com.domo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for sending {@link Employee} messages to a Kafka topic.
 */
@RestController
@RequestMapping("/api")
public class KafkaProducer {

    private final KafkaTemplate<String, Employee> kafkaTemplate;

    /**
     * Constructs a new {@code KafkaProducer} with the specified {@link KafkaTemplate}.
     *
     * @param kafkaTemplate the Kafka template used to send messages
     */
    public KafkaProducer(KafkaTemplate<String, Employee> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends an {@link Employee} object to the Kafka topic named {@code "my-topic-11"}.
     * <p>
     * The {@link Employee} is created using the provided name, and the message is sent asynchronously.
     *
     * @param name the name of the employee to send
     * @return a confirmation message indicating the message was sent, including the employee's name and ID
     */
    @PostMapping("/send")
    public String sendMessage(@RequestParam("name") String name) {
        Employee employee = new Employee(name);
        kafkaTemplate.send("my-topic-11", employee);
        return "Message sent: " + employee.name + " - " + employee.id;
    }
}
