package com.example.news.web.model.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long commentId;

    private Long newsId;

    private String userEmail;

    private String text;

    private Instant createAt;

    private Instant updateAt;

}
