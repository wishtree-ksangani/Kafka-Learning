# Kafka Producer and Consumer Example

This project demonstrates a simple Kafka Producer and Consumer setup using Spring Boot. It showcases how to send and receive messages with Kafka, specifically for an `Employee` class. It is designed for educational purposes to understand how Kafka works with Spring Boot and how to configure and implement producers and consumers.

## Learning Concepts

This project covers the following key concepts:

* **Apache Kafka**: A distributed event streaming platform used to build real-time data pipelines and streaming applications.
* **Spring Boot**: A framework that simplifies the process of building and running Java applications.
* **Kafka Producer**: An application that sends messages to a Kafka topic.
* **Kafka Consumer**: An application that listens for and processes messages from a Kafka topic.
* **Spring Kafka**: A Spring integration for Kafka, making it easier to configure Kafka producers and consumers.
* **Spring Cloud Stream**: A framework for building message-driven microservices connected with shared messaging systems.
* **Functional Programming Model in Spring Cloud Stream**: Using Java 8+ functions (`Supplier`, `Function`, `Consumer`) to declare message-producing or message-consuming behaviors.
* **JSON Serialization and Deserialization**: Converting `Employee` objects to JSON format for transmission and back to Java objects for processing.

## Important Kafka Components

1. **Producer**:

   * **Producer Record**: A Kafka producer sends messages as a `ProducerRecord`. It includes the topic name, key, and value.
   * **KafkaProducer**: This is the Kafka client that sends the `ProducerRecord` to Kafka brokers.
   * **Serializer**: The producer uses serializers to convert Java objects to byte arrays for transmission. In this case, `JsonSerializer` is used to serialize the `Employee` object to JSON format.

2. **Consumer**:

   * **KafkaListener**: A Spring annotation that automatically binds a method to listen to a specific Kafka topic.
   * **KafkaConsumer**: The Kafka consumer is responsible for consuming messages from Kafka topics.
   * **Deserializer**: The consumer uses deserializers to convert the byte array back into Java objects. In this project, `JsonDeserializer` is used to convert the JSON data back into `Employee` objects.

3. **Kafka Broker**:

   * **Brokers**: Kafka brokers manage the storage of messages and facilitate the distribution of messages across multiple consumers.
   * **Topics**: Kafka topics are logical channels for storing and sending messages. Messages are published to topics and consumed from them.
   * **Partitions**: Each topic can have multiple partitions, which allows Kafka to scale horizontally. Each partition is an ordered, immutable sequence of messages.

4. **Spring Kafka Configuration**:

   * **KafkaTemplate**: A Spring Kafka class that simplifies interaction with Kafka. It is used by the producer to send messages.
   * **KafkaListenerContainerFactory**: Configures the consumer to automatically listen for messages and process them.

## Spring Cloud Stream & Function Model

- **Stream realatred code Available in** [spring-cloud-stream branch](https://github.com/wishtree-ksangani/Kafka-Learning/tree/spring-cloud-stream)

Spring Cloud Stream simplifies messaging with Kafka and other brokers by abstracting binding logic. It enables developers to write message-driven microservices using functional Java types like `Supplier`, `Function`, and `Consumer`.

### Key Concepts:

* **Supplier**: Sends data to the message channel (used in the producer to generate `Employee` objects).
* **Consumer**: Receives data from the message channel (used in the consumer to receive and log employee data).
* **Bindings**: Define input and output channels for functions via configuration (`application.yml`).
* **Binder**: Connects the application to the external message broker like Kafka.
* **Poller**: Enables polling behavior in functions (e.g., sending data every 5 seconds).

### Example:

```java
@Bean
public Supplier<Employee> sendEmployeeDetails() {
    return () -> new Employee("Kevin");
}
```

```yaml
spring:
  cloud:
    function:
      definition: sendEmployeeDetails
    stream:
      bindings:
        sendEmployeeDetails-out-0:
          destination: my-topic
      poller:
        fixed-delay: 5000
```

## Project Structure

* **Producer**: Sends messages to a Kafka topic using a REST controller or a polling `Supplier`.
* **Consumer**: Listens to a Kafka topic using a functional `Consumer` and processes incoming messages.

### Key Components

1. **Employee Class**: Represents the `Employee` object with fields `name` and `id`.

   * This class is shared between both the producer and consumer to maintain consistency in the data format.

2. **Producer Application**:

   * **REST Controller** *(optional)*: You can expose a custom endpoint to send data via API call (not shown here, but easily integrable).
   * **Spring Cloud Stream Supplier**: Produces `Employee` data at fixed intervals using a `Supplier` function.
   * **Configuration**: Sends messages to topic `my-topic`.

3. **Consumer Application**:

   * **Spring Cloud Stream Consumer**: Listens to topic `my-new-topic` using a `Consumer<Employee>` function.
   * **Group ID**: Ensures each message is read only once per group (helps avoid duplication).
   * **Configuration**: Uses Spring Cloud Stream Kafka binder with destination and group set up.

## Kafka Setup

Apache Kafka is used as the message broker. The Producer sends messages to a Kafka topic, and the Consumer listens to that topic to receive the messages.

### Kafka Broker

* Kafka is assumed to be running locally at `localhost:9092`. Make sure Kafka is running before testing the producer and consumer.

  To start Kafka on a local machine:

   1. Download Kafka from [Kafka's official website](https://kafka.apache.org/downloads).
   2. Start the ZooKeeper instance: `bin/zookeeper-server-start.sh config/zookeeper.properties`.
   3. Start the Kafka broker: `bin/kafka-server-start.sh config/server.properties`.

### Kafka Topic Configuration

* The topic `my-topic-11` is used for communication between the producer and consumer. The topic is managed by Kafka brokers, and the producer sends messages to it. The consumer listens for messages on this topic.
* Topic `my-new-topic-intellij` is also created with 3 partitions to support scalability.

## Running the Application

1. **Start Kafka**: Make sure you have Kafka running on `localhost:9092`.
2. **Run the Producer**: Start the Producer application, which will send messages to Kafka using a polling function or API.
3. **Run the Consumer**: Start the Consumer application, which will listen for messages on Kafka.
4. **Send a Message (API method)**: Optionally, if you have an endpoint, use a tool like Postman or cURL to send a POST request to `http://localhost:8080/api/send?name=John`.

   * Example request:

     ```bash
     curl -X POST http://localhost:8080/api/send?name=John
     ```
   * Example response:

     ```text
     Message sent: John - 0
     ```
5. **Check Consumer**: The Consumer will log the received message in the console:

   ```text
   Listened from 1: John - 0
   ```

## Conclusion

This example demonstrates a simple Kafka-based messaging system with Spring Boot and Spring Cloud Stream. The Producer sends messages to a Kafka topic using a `Supplier` function, and the Consumer listens to those messages using a `Consumer` function. You can extend this setup to perform more complex operations, such as filtering, transforming messages, integrating with APIs, or interacting with databases.

By understanding how Kafka and Spring Cloud Stream work together, you can build real-time event-driven applications, process streaming data efficiently, and develop scalable, loosely-coupled microservices.
