package com.example.news.web.model.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneNewsResponse {

    private Long newsId;

    private String categoryShortDesc;

    private String userEmail;

    private String title;

    private String text;

    private List<CommentResponse> comments = new ArrayList<>();

    private Instant createAt;

    private Instant updateAt;

}
