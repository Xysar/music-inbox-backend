package com.example.music.inbox.backend.entity;

import com.example.music.inbox.backend.serializer.AlbumSerializer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "albums")
@JsonSerialize(using = AlbumSerializer.class)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String mbid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Review> reviews = new ArrayList<>();



}
