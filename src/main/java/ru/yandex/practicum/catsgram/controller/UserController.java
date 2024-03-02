package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.controller.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.controller.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;

@RestController
@RequestMapping("users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final Map<String, User> users = new HashMap<>();

    @GetMapping()
    public Collection<User> findAll() {
        log.info("Текущее количество пользователей: " + users.size());

        return users.values();
    }

    @PostMapping()
    public User create(@RequestBody User user) {
        log.info(String.valueOf(user));

        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("email already exist");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new InvalidEmailException("email not exist");
        }

        users.put(user.getEmail(), user);

        return users.get(user.getEmail());
    }

    @PutMapping()
    public User update(@RequestBody User user) {

        if (user.getEmail().isEmpty()) {
            throw new RuntimeException("email not exist");
        }

        users.put(user.getEmail(), user);

        return users.get(user.getEmail());
    }
}
