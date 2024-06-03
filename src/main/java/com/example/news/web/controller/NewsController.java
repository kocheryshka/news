package com.example.news.web.controller;

import com.example.news.mapper.NewsMapper;
import com.example.news.model.News;
import com.example.news.service.NewsService;
import com.example.news.web.model.list.NewsListResponse;
import com.example.news.web.model.single.NewsResponse;
import com.example.news.web.model.upsert.UpsertNewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(){
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(newsService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(newsMapper.newsToResponse(newsService.findById(id)));
    }

/*    @PostMapping
    public ResponseEntity<NewsResponse> save(@RequestBody UpsertNewsRequest request){
        News news = newsMapper.requestToNews(request);
        System.out.println("news:" + news);
        news = newsService.save(news);
        System.out.println("savedNews:" + news);
        NewsResponse response = newsMapper.newsToResponse(news);
        System.out.println("response:" + response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }*/

}
