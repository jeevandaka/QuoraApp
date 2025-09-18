package com.example.QuoraReactiveApp.repositories;

import com.example.QuoraReactiveApp.models.QuestionElasticDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableElasticsearchRepositories
public interface QuestionElasticRepository extends ElasticsearchRepository<QuestionElasticDocument,String> {
    List<QuestionElasticDocument> findByTitleContainingOrContentContaining(String title, String content);
}
