package com.example.news.mapper;

import com.example.news.model.Category;
import com.example.news.model.News;
import com.example.news.model.User;
import com.example.news.service.CategoryService;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import com.example.news.utils.BeanUtils;
import com.example.news.web.model.upsert.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NewsMapperDelegate implements NewsMapper{

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NewsService newsService;

    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new News();
        BeanUtils.copyNonNullProperties(request, news);
        System.out.println("    request.getUserEmail()" + request.getUserEmail());
        if(request.getUserEmail() != null) {
            User user = userService.findById(request.getUserEmail());
            news.setUser(user);
        }
        if(request.getCategoryId() != null){
            Category category = categoryService.findById(request.getCategoryId());
            news.setCategory(category);
        }

        return news;
    }

    @Override
    public News requestToNews(Long newsId, UpsertNewsRequest request) {
        News existingNews = newsService.findById(newsId);
        News news = requestToNews(request);
        BeanUtils.copyNonNullProperties(news, existingNews);

        return existingNews;
    }
}
