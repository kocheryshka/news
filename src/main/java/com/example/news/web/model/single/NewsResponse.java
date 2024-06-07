package com.example.news.web.model.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsResponse {

    private Long newsId;

    //private Long categoryId;

    private String categoryShortDesc;

    private String userEmail;

    private String title;

    private String text;

    private Integer commentsCount;

    private Instant createAt;

    private Instant updateAt;

}
