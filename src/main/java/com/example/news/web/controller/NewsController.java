package com.example.news.web.controller;

import com.example.news.mapper.NewsMapper;
import com.example.news.model.News;
import com.example.news.service.NewsService;
import com.example.news.web.model.list.NewsListResponse;
import com.example.news.web.model.single.OneNewsResponse;
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
    public ResponseEntity<OneNewsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(newsMapper.oneNewsToResponse(newsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OneNewsResponse> save(@RequestBody UpsertNewsRequest request){
        News news = newsService.save(newsMapper.requestToNews(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.oneNewsToResponse(news));
    }

    @PutMapping("/{newsId}")
    public ResponseEntity<OneNewsResponse> update(@PathVariable Long newsId, @RequestBody UpsertNewsRequest request){
        News news = newsService.findById(newsId);
        newsMapper.requestToNews(newsId, request, news);
        news = newsService.save(news);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newsMapper.oneNewsToResponse(news));
    }

    @DeleteMapping("/{newsId}")
    public ResponseEntity<Void> delete(@PathVariable Long newsId){
        newsService.deleteById(newsId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
