package com.example.QuoraReactiveApp.services;

import com.example.QuoraReactiveApp.dto.QuestionRequestDTO;
import com.example.QuoraReactiveApp.dto.QuestionResponseDTO;
import com.example.QuoraReactiveApp.models.Question;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface IQuestionService {
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO question);
    public Mono<QuestionResponseDTO> getQuestionById(String id);
    public Flux<QuestionResponseDTO> getAllQuestions(LocalDateTime cursor, int size);
    public Flux<QuestionResponseDTO> searchQuestions(String query, int page, int size);
    public Flux<QuestionResponseDTO> getTopTenRecords(int size);
}
