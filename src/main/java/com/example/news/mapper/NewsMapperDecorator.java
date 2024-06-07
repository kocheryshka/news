package com.example.news.mapper;

import com.example.news.model.News;
import com.example.news.service.CategoryService;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import com.example.news.web.model.single.NewsResponse;
import com.example.news.web.model.upsert.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NewsMapperDecorator implements NewsMapper{

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsMapper delegate;

    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse response = delegate.newsToResponse(news);
        response.setCommentsCount(news.getComments().size());
        response.setCategoryShortDesc(news.getCategory().getShortDesc());
        return response;
    }

    @Override
    public News requestToNews(UpsertNewsRequest request) {

        News news = delegate.requestToNews(request);
        news.setCategory(categoryService.findById(request.getCategoryId()));
        news.setUser(userService.findById(request.getUserEmail()));

        return news;
    }

    @Override
    public void requestToNews(Long newsId, UpsertNewsRequest request, News news) {
        delegate.requestToNews(newsId, request, news);
        if (request.getCategoryId() != null){
            news.setCategory(categoryService.findById(request.getCategoryId()));
        }
    }

}
