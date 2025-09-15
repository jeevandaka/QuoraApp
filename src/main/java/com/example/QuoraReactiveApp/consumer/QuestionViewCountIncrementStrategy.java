package com.example.QuoraReactiveApp.consumer;

import com.example.QuoraReactiveApp.event.ViewCountEvent;
import com.example.QuoraReactiveApp.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("question")
@Scope("prototype")
@RequiredArgsConstructor
public class QuestionViewCountIncrementStrategy implements IViewCountIncrStrategy{


    private final QuestionRepository questionRepository;
    private ViewCountEvent viewCountEvent;

    @Override
    public void increaseViewCount() {
        // question view count increment
        questionRepository.findById(viewCountEvent.getTargetId())
            .flatMap(question -> {
                question.setViews(question.getViews() == null ? 0 : question.getViews() + 1);
                return questionRepository.save(question);
            })
            .subscribe(updatedQuestion ->{
                System.out.println("View count incremented for question: " + updatedQuestion.getId());
            }, error -> {
                System.out.println("Error incrementing view count for question: " + error.getMessage());
            });
    }

    @Override
    public void setViewCountEvent(ViewCountEvent event) {
        this.viewCountEvent = event;
    }
}
