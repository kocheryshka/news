package com.example.news.web.model.single;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private Long categoryId;

    private String shortDesc;

    private String description;

    private List<OneNewsResponse> news;

    private Instant createAt;

    private Instant updateAt;

}
