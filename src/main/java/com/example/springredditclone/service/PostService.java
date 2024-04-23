package com.example.springredditclone.service;

import com.example.springredditclone.dto.PostRequest;
import com.example.springredditclone.dto.PostResponse;
import com.example.springredditclone.exceptions.PostNotFoundException;
import com.example.springredditclone.exceptions.SubredditNotFoundException;
import com.example.springredditclone.mapper.PostMapper;
import com.example.springredditclone.model.Post;
import com.example.springredditclone.model.Subreddit;
import com.example.springredditclone.model.User;
import com.example.springredditclone.repository.PostRepository;
import com.example.springredditclone.repository.SubredditRepository;
import com.example.springredditclone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final AuthService authService;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;

    public Post save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(()-> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();

       return postMapper.map(postRequest,subreddit,currentUser);

    }

    public PostResponse getPost(Long postId) {

        return postMapper.mapToDto(postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString())));
    }

    public List<PostResponse> getAllPosts() {

        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    public List<PostResponse> getPostsBySubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(()-> new SubredditNotFoundException(id.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    public List<PostResponse> getPostsByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));

        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }
}
