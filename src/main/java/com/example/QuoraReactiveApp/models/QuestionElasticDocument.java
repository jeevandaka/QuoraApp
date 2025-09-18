package com.example.QuoraReactiveApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "question")
public class QuestionElasticDocument {

    @Id
    private String id;

    private String title;
    private String content;

}
