package com.example.news.service.impl;

import com.example.news.model.Category;
import com.example.news.model.News;
import com.example.news.model.User;
import com.example.news.repository.NewsRepository;
import com.example.news.service.CategoryService;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import com.example.news.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException(MessageFormat.format(
                "Новость с ID {0} не найдена!", id))
        );
    }

    @Override
    public News save(News news) {
        User user = userService.findById(news.getUser().getEmail());
        news.setUser(user);

        Category category = categoryService.findById(news.getCategory().getCategoryId());
        news.setCategory(category);

        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        User user = userService.findById(news.getUser().getEmail());
        Category category = categoryService.findById(news.getCategory().getCategoryId());

        News existingNews = findById(news.getNewsId());
        BeanUtils.copyNonNullProperties(news, existingNews);
        existingNews.setUser(user);
        existingNews.setCategory(category);

        return newsRepository.save(existingNews);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
