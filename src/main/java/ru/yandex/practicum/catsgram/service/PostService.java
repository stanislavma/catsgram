package ru.yandex.practicum.catsgram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.catsgram.controller.PostController;
import ru.yandex.practicum.catsgram.controller.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private static final Logger log = LoggerFactory.getLogger(PostService.class);

    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;
    private static Integer globalId = 0;

    private static Integer getNextId() {
        return ++globalId;
    }

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(String sort, int size, int from) {
        Comparator<Post> comparator = Comparator.comparing(Post::getCreationDate);
        if ("desc".equalsIgnoreCase(sort)) {
            comparator = comparator.reversed();
        }

        return posts.stream()
                .sorted(comparator)
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());

    }

    public Optional<Post> findById(int postId) {
        return posts.stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
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

        post.setId(getNextId());
        posts.add(post);

        return post;
    }

}