package com.example.QuoraReactiveApp.controller;

import com.example.QuoraReactiveApp.dto.QuestionRequestDTO;
import com.example.QuoraReactiveApp.dto.QuestionResponseDTO;
import com.example.QuoraReactiveApp.services.IQuestionService;
import com.example.QuoraReactiveApp.services.QuestionService;
import com.example.QuoraReactiveApp.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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

    @GetMapping("/getAll")
    public Flux<QuestionResponseDTO> getAllQuestions( // cursor based pagination
            @RequestParam String cursor,
            @RequestParam(defaultValue = "10") int size
    ){

        if(!CursorUtils.isValidCursor(cursor)){
            System.out.println(cursor + "Cursor is invalid");
            return QuestionService.getTopTenRecords(size);
        }
        else{
            LocalDateTime cursorTimeStamp = CursorUtils.parseCursor(cursor);
            return QuestionService.getAllQuestions(cursorTimeStamp,size)
                    .doOnError(error -> System.out.println("error at controller"))
                    .doOnSubscribe(response -> System.out.println("subscribed at controller " + response));
        }

    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestions( // offset based pagination
            @RequestParam String query,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
    ){
        return QuestionService.searchQuestions(query,page,size)
                .doOnError(error -> System.out.println("error"))
                .doOnSubscribe(response -> System.out.println("subscribed at controller " + response));
    }
}
