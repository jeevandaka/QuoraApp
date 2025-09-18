package com.example.QuoraReactiveApp.services;

import com.example.QuoraReactiveApp.models.Question;
import com.example.QuoraReactiveApp.models.QuestionElasticDocument;

import java.util.List;

public interface IQuestionElasticService {
    void createQuestionIndex(Question question);
    List<QuestionElasticDocument> getQuestions(String query);
}
