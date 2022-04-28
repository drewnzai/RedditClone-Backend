package com.drew.Reddit.repositories;


import com.drew.Reddit.models.Post;
import com.drew.Reddit.models.Subreddit;
import com.drew.Reddit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
