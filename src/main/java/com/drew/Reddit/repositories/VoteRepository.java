package com.drew.Reddit.repositories;


import com.drew.Reddit.models.Post;
import com.drew.Reddit.models.User;
import com.drew.Reddit.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User user);
}
