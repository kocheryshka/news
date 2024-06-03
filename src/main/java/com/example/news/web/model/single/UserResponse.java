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
public class UserResponse {

    private String email;

    private String name;

    private String phone;

    private Instant createAt;

    private Instant updateAt;

    private List<NewsResponse> news = new ArrayList<>();

}
