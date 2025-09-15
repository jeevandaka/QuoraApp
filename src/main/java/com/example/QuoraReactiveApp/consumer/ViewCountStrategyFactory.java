package com.example.QuoraReactiveApp.consumer;

import com.example.QuoraReactiveApp.event.ViewCountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ViewCountStrategyFactory {

    private final Map<String, IViewCountIncrStrategy> strategies;

    public IViewCountIncrStrategy getStrategy(ViewCountEvent event){

        System.out.println("ViewCountStrategyFactory : " + strategies);
        IViewCountIncrStrategy strategy = strategies.get(event.getTargetType());
        strategy.setViewCountEvent(event);
        return strategy;
    }

}
