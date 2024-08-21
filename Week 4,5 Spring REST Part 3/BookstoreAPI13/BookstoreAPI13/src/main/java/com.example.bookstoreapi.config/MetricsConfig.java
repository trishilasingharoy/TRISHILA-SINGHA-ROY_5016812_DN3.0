package com.example.bookstoreapi.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public void customMetrics(MeterRegistry registry) {
        registry.counter("books.created.count", "type", "book");
        registry.gauge("books.total.count", bookRepository.count());
    }
}
