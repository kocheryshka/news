package com.example.news.service.impl;

import com.example.news.model.Comment;
import com.example.news.model.News;
import com.example.news.repository.CommentRepository;
import com.example.news.service.CommentService;
import com.example.news.service.NewsService;
import com.example.news.utils.BeanUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentsServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final NewsService newsService;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Комментарий с ID {0} не найден!", id))
        );
    }

    @Override
    public Comment save(Comment comment) {
        News news = newsService.findById(comment.getNews().getNewsId());
        comment.setNews(news);

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        News news = newsService.findById(comment.getNews().getNewsId());
        Comment existingComment = findById(comment.getCommentId());
        BeanUtils.copyNonNullProperties(comment, existingComment);
        existingComment.setNews(news);

        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

}
