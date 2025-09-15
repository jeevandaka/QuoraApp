package com.example.QuoraReactiveApp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "likes")
public class Like {

    @Id
    private String id;

    private String targetId;

    //TODO : add enm for target type
    private LikeTargetType targetType; // Question, Answer
    private boolean isLike;

    @CreatedDate
    private LocalDateTime createdAt;
}
