package com.drew.Reddit.services;

import com.drew.Reddit.dto.TrendingItemsDto;
import com.drew.Reddit.exceptions.SpringRedditException;
import com.drew.Reddit.mapper.TrendingItemsMapper;
import com.drew.Reddit.models.TrendingItem;
import com.drew.Reddit.repositories.TrendingItemsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class TrendingItemsService {
    
    private final TrendingItemsRepository repository;
    
    private final TrendingItemsMapper mapper;
    @Transactional(readOnly = true)
    public List<TrendingItemsDto> getAll(){
        return repository.findAll()
                .stream()
                .map(mapper::mapTrendingItemtoDto)
                .collect(toList());
    }

    @Transactional
    public TrendingItemsDto save(TrendingItemsDto trendingItemsDto) {
        TrendingItem save = repository.save(mapper.mapDtotoTrendingItem(trendingItemsDto));
        trendingItemsDto.setId(save.getId());
        return trendingItemsDto;
    }

    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public TrendingItemsDto getTrendingItem(Long id) {
        TrendingItem trendingItem = repository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No TrendingItem found with ID - " + id));
        return mapper.mapTrendingItemtoDto(trendingItem);
    }
}


