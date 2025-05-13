package com.domo.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

/**
 * Configuration class that defines functional beans for use with Spring Cloud Stream or other functional interfaces.
 */
@Configuration
public class FunctionsClass {

    /**
     * Returns a function that converts a given string to its uppercase equivalent.
     * <p>
     * This bean can be used in functional programming models, such as Spring Cloud Stream functional style,
     * where functions are exposed as message processors.
     *
     * @return a {@link Function} that takes a {@link String} and returns the same string in uppercase
     */
    @Bean
    public Function<String, String> uppercase() {
        return String::toUpperCase;
    }
}
