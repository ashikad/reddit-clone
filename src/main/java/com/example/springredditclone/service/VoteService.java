package com.example.springredditclone.service;


import com.example.springredditclone.dto.VoteDto;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;


    public void vote(VoteDto voteDto) {

    }
}
