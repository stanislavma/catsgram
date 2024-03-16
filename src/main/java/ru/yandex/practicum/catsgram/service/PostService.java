package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.PostController;
import ru.yandex.practicum.catsgram.controller.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        try {
            if (userService.getUserByEmail(post.getAuthor()) == null) {
                throw new UserNotFoundException("«Пользователь " + post.getAuthor() + " не найден».");
            }
        } catch (UserNotFoundException e) {
          log.error(e.getMessage());
          return post;
        }

        posts.add(post);

        return post;
    }
}