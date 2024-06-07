package com.example.news.mapper;

import com.example.news.model.Comment;
import com.example.news.web.model.list.CommentListResponse;
import com.example.news.web.model.single.CommentResponse;
import com.example.news.web.model.upsert.UpsertCommentRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", typeConversionPolicy = ReportingPolicy.IGNORE)
@DecoratedWith(CommentMapperDecorator.class)
public interface CommentMapper {

    @Mapping(source = "comment.user.email", target = "userEmail")
    @Mapping(source = "comment.news.newsId", target = "newsId")
    CommentResponse commentToResponse(Comment comment);

    Comment requestToComment(UpsertCommentRequest request);

    Comment requestToComment(Long commentId, UpsertCommentRequest request);

    default CommentListResponse commentListToCommentListResponse(List<Comment> comments){
        CommentListResponse commentListResponse = new CommentListResponse();
        commentListResponse.setComments(comments.stream()
                .map(this::commentToResponse)
                .toList());

        return commentListResponse;
    }
}
