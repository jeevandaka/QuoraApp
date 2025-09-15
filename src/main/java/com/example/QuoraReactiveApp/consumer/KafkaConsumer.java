package com.example.QuoraReactiveApp.consumer;

import com.example.QuoraReactiveApp.config.KafkaConfig;
import com.example.QuoraReactiveApp.event.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ViewCountStrategyFactory factory;

    @KafkaListener(
        topics = KafkaConfig.TOPIC_NAME,
        groupId = "${spring.kafka.consumer.group-id:view-count-consumer}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeViewCountEvent(ViewCountEvent viewCountEvent){
        System.out.println("KafkaConsumer : " + viewCountEvent.getTargetType() );
        factory.getStrategy(viewCountEvent).increaseViewCount();
    }
}
