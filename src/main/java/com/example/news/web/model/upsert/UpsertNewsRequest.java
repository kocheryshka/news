package com.example.news.web.model.upsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertNewsRequest {

    private Long categoryId;

    private String userEmail;

    private String title;

    private String text;

}
