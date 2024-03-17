package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll(@RequestParam(defaultValue = "asc", required = false) String sort,
                              @RequestParam(defaultValue = "10", required = false) Integer size,
                              @RequestParam(defaultValue = "0", required = false) Integer page) {

        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new IllegalArgumentException();
        }
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException();
        }

        int from = size * page;

        return postService.findAll(sort, size, from);
    }

    @GetMapping("/posts/{postId}")
    public Optional<Post> findById(@PathVariable int postId) {
        return postService.findById(postId);
    }

    @GetMapping("/posts/search1")
    public List<Post> searchPosts(@RequestParam String author) {
        System.out.println("Ищем посты пользователя с именем " + author);
//    ... // опустим логику поиска
        return null;
    }

    @GetMapping("/posts/search")
    public List<Post> searchPosts(
            @RequestParam String author,
            @RequestParam
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        System.out.println("Ищем посты пользователя с именем " + author +
                " и опубликованные " + date);
//    ... // опустим логику поиска

        return null;
    }

    @PostMapping(value = "/post")
    public Post create(@RequestBody Post post) {
        log.info(String.valueOf(post));

        return postService.create(post);
    }
}