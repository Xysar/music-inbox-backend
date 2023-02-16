package com.example.music.inbox.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumModel {
    private String name;
    private String artistName;
    private Integer rating;
}