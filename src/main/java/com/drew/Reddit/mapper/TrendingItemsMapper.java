package com.drew.Reddit.mapper;

import com.drew.Reddit.dto.TrendingItemsDto;
import com.drew.Reddit.exceptions.SpringRedditException;
import com.drew.Reddit.models.Subreddit;
import com.drew.Reddit.models.TrendingItem;
import com.drew.Reddit.repositories.CommentRepository;
import com.drew.Reddit.repositories.SubredditRepository;
import lombok.AllArgsConstructor;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = { SubredditRepository.class})
public abstract class TrendingItemsMapper {
    @Autowired
    SubredditRepository repository;
    @Mapping(target="image_name", source = "image_src")
    @Mapping(target = "subreddit_name", expression = "java(getName(trendingItem.getSubreddit()))")
    @Mapping(target = "subreddit_description", expression = "java(getDescription(trendingItem.getSubreddit()))")
    public abstract TrendingItemsDto mapTrendingItemtoDto(TrendingItem trendingItem);

     String getName(Subreddit subreddit){
        return subreddit.getName();
    }

    String getDescription(Subreddit subreddit){
        return subreddit.getDescription();
    }

    Subreddit getSubreddit(String name){
        return repository.findByName(name)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with name - " + name));
    }

    @InheritInverseConfiguration
    @Mapping(target = "subreddit", expression = "java(getSubreddit(trendingItemsDto.getSubreddit_name()))")
    public abstract TrendingItem mapDtotoTrendingItem(TrendingItemsDto trendingItemsDto);
}
