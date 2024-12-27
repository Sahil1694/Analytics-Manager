package com.Analyzer.analyticsgenerator.controller;

import org.springframework.kafka.event.KafkaEvent;
import org.springframework.stereotype.Service;
import io.prometheus.client.Counter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerController {
    private final Counter kafkaEventCounter;

    public ConsumerController(){
        kafkaEventCounter = Counter.build()
                .name("kafka_event_counter")
                .help("Total number of kafka events consumed")
                .register();
    }

    @KafkaListener(topics = "analytics",groupId = "metrics-consumer-group")
    public void lister(String eventData){
        System.out.println("Received event: " + eventData);
        kafkaEventCounter.inc();
    }


}
