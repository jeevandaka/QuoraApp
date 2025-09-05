package com.example.QuoraReactiveApp.controller;

import com.example.QuoraReactiveApp.dto.QuestionRequestDTO;
import com.example.QuoraReactiveApp.dto.QuestionResponseDTO;
import com.example.QuoraReactiveApp.services.IQuestionService;
import com.example.QuoraReactiveApp.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

    private final IQuestionService QuestionService;

    @PostMapping("/add")
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO){
        return QuestionService.createQuestion(questionRequestDTO)
                .doOnSuccess(response-> System.out.println("Success"));
    }

    @GetMapping("/getQuestion")
    public Mono<QuestionResponseDTO> getQuestionById(@RequestParam(name = "id") String id){
        return QuestionService.getQuestionById(id)
                .doOnSuccess(response -> System.out.println("Question is ready to send"))
                .doOnError(error -> System.out.println("Error encounter in sending question from controller : "+ error));
    }
}
