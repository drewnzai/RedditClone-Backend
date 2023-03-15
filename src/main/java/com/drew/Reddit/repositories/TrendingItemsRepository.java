package com.drew.Reddit.repositories;

import com.drew.Reddit.models.TrendingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrendingItemsRepository extends JpaRepository<TrendingItem, Long> {
}
