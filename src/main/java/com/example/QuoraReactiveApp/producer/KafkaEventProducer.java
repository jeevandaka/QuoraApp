package com.example.QuoraReactiveApp.producer;

import com.example.QuoraReactiveApp.config.KafkaConfig;
import com.example.QuoraReactiveApp.event.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaEventProducer {
    public KafkaTemplate<String,Object> kafkaTemplate;

    public void publishViewCountEvent(ViewCountEvent viewCountEvent){
        kafkaTemplate.send(KafkaConfig.TOPIC_NAME, viewCountEvent.getTargetId(), viewCountEvent)
                .whenComplete((result,error)->{
                    if(error!= null){
                        System.out.println("Error in publishing view count " + error.getMessage());
                    }
                });
    }
}
