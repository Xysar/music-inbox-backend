package com.example.music.inbox.backend.service;

import com.example.music.inbox.backend.entity.User;
import com.example.music.inbox.backend.model.UserModel;

public interface UserService {

    User registerUser(UserModel userModel);
}
