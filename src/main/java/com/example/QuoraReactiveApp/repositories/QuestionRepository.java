package com.example.QuoraReactiveApp.repositories;

import com.example.QuoraReactiveApp.models.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question,String> {

    Flux<Question> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
//    Mono<Long> creatAuthorId(String authorId);
    Flux<Question> findByCreatedAtGreaterThanOrderByCreatedAtAsc(LocalDateTime createdAt, Pageable pageable);

    Flux<Question> findTop10ByOrderByCreatedAtDesc();
}
