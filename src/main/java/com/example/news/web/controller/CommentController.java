package com.example.news.web.controller;

import com.example.news.mapper.CommentMapper;
import com.example.news.model.Comment;
import com.example.news.service.CommentService;
import com.example.news.web.model.list.CommentListResponse;
import com.example.news.web.model.single.CommentResponse;
import com.example.news.web.model.upsert.UpsertCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<CommentListResponse> findAll(){
        return ResponseEntity.ok(commentMapper.commentListToCommentListResponse(commentService.findAll()));
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long commentId){
        return ResponseEntity.ok(commentMapper.commentToResponse(commentService.findById(commentId)));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> save(@RequestBody UpsertCommentRequest request){
        Comment comment = commentService.save(commentMapper.requestToComment(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentToResponse(comment));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long commentId, @RequestBody UpsertCommentRequest request){
        Comment comment = commentService.findById(commentId);
        comment.setText(request.getText());

        return ResponseEntity.ok(commentMapper.commentToResponse(commentService.save(comment)));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> delete(@PathVariable Long commentId){
        commentService.deleteById(commentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
