package com.example.news.web.model.upsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertCommentRequest {

    private String userEmail;

    private Long newsId;

    private String text;

}
