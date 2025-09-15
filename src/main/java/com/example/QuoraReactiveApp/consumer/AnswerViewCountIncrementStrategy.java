package com.example.QuoraReactiveApp.consumer;

import com.example.QuoraReactiveApp.event.ViewCountEvent;
import com.example.QuoraReactiveApp.repositories.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("answer")
@Scope("prototype")
@RequiredArgsConstructor
public class AnswerViewCountIncrementStrategy implements IViewCountIncrStrategy{

    private final AnswerRepository answerRepository;
    private ViewCountEvent viewCountEvent;


    @Override
    public void increaseViewCount() {
        String id = viewCountEvent.getTargetId();
        // increment answer view count
    }

    @Override
    public void setViewCountEvent(ViewCountEvent event) {
        this.viewCountEvent = event;
    }
}
