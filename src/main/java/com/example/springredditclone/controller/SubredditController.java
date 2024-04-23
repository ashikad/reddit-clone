package com.example.springredditclone.controller;


import com.example.springredditclone.dto.SubredditDto;
import com.example.springredditclone.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDto));

    }

    @GetMapping
    public void getAllSubreddits(){
        subredditService.getAll();
    }

    @GetMapping
    public void getSubreddit(@PathVariable Long id){
        subredditService.getSubreddit(id);
    }

}
