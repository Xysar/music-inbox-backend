package com.example.music.inbox.backend.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;

    private String name;
    private String artistName;
    private Integer rating;


}
