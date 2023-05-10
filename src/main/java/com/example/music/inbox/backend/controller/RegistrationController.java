package com.example.music.inbox.backend.controller;

import com.example.music.inbox.backend.entity.Album;
import com.example.music.inbox.backend.entity.Review;
import com.example.music.inbox.backend.entity.User;
import com.example.music.inbox.backend.model.AlbumModel;
import com.example.music.inbox.backend.model.ReviewModel;
import com.example.music.inbox.backend.model.UserModel;
import com.example.music.inbox.backend.repository.AlbumRepository;
import com.example.music.inbox.backend.repository.ReviewRepository;
import com.example.music.inbox.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/","https://music-inbox-nextjs-tsiq.vercel.app' "})
public class RegistrationController {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/api/create-user")
    public User createUser(@RequestBody UserModel userModel){
        User newUser = new User();
        newUser.setUsername(userModel.getUsername());
        newUser.setClerkId(userModel.getClerkId());
        try{userRepository.save(newUser);} catch ( Exception e){
            User blankUser = new User();
            return blankUser;
        };
        return newUser;
    }

    @PostMapping("/api/create-album")
    public Album createAlbum(@RequestBody AlbumModel albumModel){
        Album newAlbum = new Album();
        newAlbum.setTitle(albumModel.getTitle());
        newAlbum.setArtist(albumModel.getArtist());
        newAlbum.setMbid(albumModel.getMbid());
       try{ albumRepository.save(newAlbum);} catch(Exception e){
           Album album = albumRepository.findByMbid(albumModel.getMbid());
           return album;
       }
        return newAlbum;
    }
    @PostMapping("/api/{musicID}/{userID}/create-review")
    public Review createReview(@PathVariable("musicID") String musicId,
                              @PathVariable("userID") String userId,
                              @RequestBody ReviewModel reviewModel){

        Review newReview = new Review();
        newReview.setContent(reviewModel.getContent());
        newReview.setRating(reviewModel.getRating());
        User user = userRepository.findByClerkId(userId);

        if(user == null){
            throw new NoSuchElementException();
        }
        newReview.setUser(user);
      Album album  = albumRepository.findByMbid(musicId);

        if(album==null){
            throw new NoSuchElementException();
        }
        newReview.setAlbum(album);
        reviewRepository.save(newReview);
        return newReview;
    }

    @DeleteMapping("/api/delete-review/{review-id}")
    public Review deleteReview(@PathVariable("review-id") Long id){

       Optional<Review> optionalReview = reviewRepository.findById(id);
        if(optionalReview.isEmpty()){
            throw new NoSuchElementException();
        }
        Review reviewToDelete = optionalReview.get();
        reviewRepository.deleteById(id);
        return reviewToDelete;
    }
    @GetMapping("/api/get-users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/api/get-user/{id}")
    public User getUser(@PathVariable("id") String userId){
       User user = userRepository.findByClerkId(userId);
        if(user==null){
            throw new NoSuchElementException();
        }
        return user;
    }

    @GetMapping("/api/get-album/{id}")
    public Album getAlbum(@PathVariable("id") Long albumId){
        Optional<Album> albumOptional = albumRepository.findById(albumId);
        if(albumOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return albumOptional.get();
    }

    @GetMapping("/api/get-album-by-mbid/{mbid}")
    public Album getAlbumByMbid(@PathVariable("mbid") String albumId){
        Album album = albumRepository.findByMbid(albumId);
        if(album == null){
            return null;
        }
        return album;
    }

    @GetMapping("/api/get-user-album-mbids/{userId}")
    public List<String> getUserAlbumMbids (@PathVariable("userId") String userId){
        User user= userRepository.findByClerkId(userId);
        if(user ==null){
            return null;
        }
        List<Review> reviews = user.getReviews();
        List<String> result = new ArrayList<>();
        for(Review e : reviews){
            result.add(e.getAlbum().getMbid());
        }
        System.out.println(result);
        return result;

    }

    @GetMapping("/api/get-review/{id}")
    public Review getReview(@PathVariable("id") Long reviewId){
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        return reviewOptional.get();
    }


}
