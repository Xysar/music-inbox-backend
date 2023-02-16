package com.example.music.inbox.backend.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    @Column(length = 60)
    private String userName;
    @Column(length = 60)
    private String password;
    private String role;

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL ,orphanRemoval=true)
    private Set<Album> albums;

    private boolean enabled = false;
}
