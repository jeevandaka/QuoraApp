package com.example.QuoraReactiveApp.services;

import com.example.QuoraReactiveApp.models.Question;
import com.example.QuoraReactiveApp.models.QuestionElasticDocument;
import com.example.QuoraReactiveApp.repositories.QuestionElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionElasticService implements IQuestionElasticService{

    private final QuestionElasticRepository questionElasticRepository;

    @Override
    public void createQuestionIndex(Question question) {
        QuestionElasticDocument questionElasticDocument = QuestionElasticDocument
                .builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .build();

        questionElasticRepository.save(questionElasticDocument);
    }

    @Override
    public List<QuestionElasticDocument> getQuestions(String query) {
        System.out.println("Elastic Service : " + query);
        List<QuestionElasticDocument> list = questionElasticRepository.findByTitleContainingOrContentContaining(query,query);
        System.out.println("Elastic Service : " + list);
        return list;
    }
}
