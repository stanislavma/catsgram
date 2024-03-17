package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.FriendFeed;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostFeedService;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostFeedController {
    private static final Logger log = LoggerFactory.getLogger(PostFeedController.class);

    private final PostFeedService postFeedService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public PostFeedController(PostFeedService postFeedService) {
        this.postFeedService = postFeedService;
    }

    @PostMapping(value = "/feed/friends")
    public List<Post> getFriendPosts(@RequestBody String body) {
        log.info(String.valueOf(body));
        try {
            String cleanJsonString = body.trim()
                    .replaceAll("^\"+|\"+$", "")
                    .replace("\\\"", "\"");

            FriendFeed friendFeed = objectMapper.readValue(cleanJsonString, FriendFeed.class);

            return postFeedService.findAll(friendFeed.getSort(), friendFeed.getSize(), friendFeed.getFriends());
        } catch (Exception e) {
            log.error("Error", e);
        }

        return new ArrayList<>();
    }
}