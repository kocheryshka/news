package com.example.news.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity(name = "comments")
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    @CreationTimestamp
    @Column(name = "create_dttm")
    private Instant createAt;

    @UpdateTimestamp
    @Column(name = "update_dttm")
    private Instant updateAt;


}
