package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.*;

@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public Collection<User> findAll() {
        log.info("Текущее количество пользователей: " + userService.findAll().size());

        return userService.findAll();
    }

    @GetMapping("/posts/{email}")
    public User findByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping()
    public User create(@RequestBody User user) {
        log.info(String.valueOf(user));

        return userService.create(user);
    }

    @PutMapping()
    public User update(@RequestBody User user) {
        log.info(String.valueOf(user));

        return userService.update(user);
    }
}
