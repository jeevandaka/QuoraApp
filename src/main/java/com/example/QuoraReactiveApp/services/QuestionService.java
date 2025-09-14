package com.example.QuoraReactiveApp.services;

import com.example.QuoraReactiveApp.adapter.QuestionAdapter;
import com.example.QuoraReactiveApp.dto.QuestionRequestDTO;
import com.example.QuoraReactiveApp.dto.QuestionResponseDTO;
import com.example.QuoraReactiveApp.event.ViewCountEvent;
import com.example.QuoraReactiveApp.models.Question;
import com.example.QuoraReactiveApp.producer.KafkaEventProducer;
import com.example.QuoraReactiveApp.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{
    private final QuestionRepository questionRepository;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO question) {

        Question question1 = Question.builder()
                .title(question.getTitle())
                .content(question.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question1)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(questionResponseDTO -> System.out.println("Question created successfully"))
                .doOnError(error -> System.out.println("error creating question: " + error));
    }

    @Override
    public Mono<QuestionResponseDTO> getQuestionById(String id) {
        return questionRepository.findById(id)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSuccess(response -> {
                        System.out.println("Question fetched successfully : " + response);
                        ViewCountEvent viewCountEvent = new ViewCountEvent(id, "question",LocalDateTime.now());
                        kafkaEventProducer.publishViewCountEvent(viewCountEvent);
                    }
                )
                .doOnError(error -> System.out.println("Fetching of question failed : " + error));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions(LocalDateTime cursor, int size){
        Pageable pageable = PageRequest.of(0,size);
        return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursor, pageable)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSubscribe(response -> System.out.println("Subscribed"))
                .doOnError(error -> System.out.println("Error in getting all question"));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String query, int offset, int size) {
        return questionRepository.findByTitleContainingOrContentContaining(query,query, PageRequest.of(offset,size))
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnSubscribe(response -> System.out.println("success"))
                .doOnError(error -> System.out.println("error"));
    }

    @Override
    public Flux<QuestionResponseDTO> getTopTenRecords(int size) {
        return questionRepository.findTop10ByOrderByCreatedAtDesc().take(size)
                .map(QuestionAdapter::toQuestionResponseDTO)
                .doOnComplete(() -> System.out.println("success"))
                .doOnError(error -> System.out.println("error"));
    }
}
