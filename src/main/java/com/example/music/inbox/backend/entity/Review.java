package com.example.music.inbox.backend.entity;

import com.example.music.inbox.backend.serializer.ReviewSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "reviews")
@Data
@JsonSerialize(using = ReviewSerializer.class)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=3000)
    private String content;

    @Column(nullable = false)
    private Integer rating;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "album_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

}