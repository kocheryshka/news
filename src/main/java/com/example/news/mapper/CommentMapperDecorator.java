package com.example.news.mapper;

import com.example.news.model.Comment;
import com.example.news.service.NewsService;
import com.example.news.service.UserService;
import com.example.news.web.model.upsert.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDecorator implements CommentMapper{

    @Autowired
    private CommentMapper delegate;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @Override
    public Comment requestToComment(UpsertCommentRequest request) {
        Comment comment = delegate.requestToComment(request);
        comment.setNews(newsService.findById(request.getNewsId()));
        comment.setUser(userService.findById(request.getUserEmail()));

        return comment;
    }

}
