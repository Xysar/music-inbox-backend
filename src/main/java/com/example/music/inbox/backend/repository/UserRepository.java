package com.example.music.inbox.backend.repository;

import com.example.music.inbox.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String userName);
    User findByClerkId(String Id);
}
