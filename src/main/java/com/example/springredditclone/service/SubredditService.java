package com.example.springredditclone.service;

import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.exceptions.SpringRedditException;
import com.example.springredditclone.mapper.SubredditMapper;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.repository.SubredditRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor

@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto){
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        subredditRepository.save(subreddit);
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public void getAll() {
        subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }


    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }


}
