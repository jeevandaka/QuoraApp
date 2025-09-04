package com.example.QuoraReactiveApp.repositories;

import com.example.QuoraReactiveApp.models.Question;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question,String> {
//    Flux<Question> findByAuthorId(String authorId);
//    Mono<Long> creatAuthorId(String authorId);
}
