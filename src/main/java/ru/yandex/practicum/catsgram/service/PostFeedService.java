package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostFeedService {
    private static final Logger log = LoggerFactory.getLogger(PostFeedService.class);

    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;
    private final PostService postService;

    private static Integer globalId = 0;

    private static Integer getNextId() {
        return ++globalId;
    }

    @Autowired
    public PostFeedService(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    public List<Post> findAll(String sort, int size, List<String> authorList) {
        List<Post> posts = postService.findAll(sort, size, 0);

        List<Post> postsFiltered = posts.stream()
                .filter(post -> authorList.contains(post.getAuthor()))
                .collect(Collectors.toList());

        return postsFiltered;

    }

}