package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.controller.exceptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.controller.exceptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public User create(User user) {
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("email already exist");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new InvalidEmailException("email not exist");
        }

        users.put(user.getEmail(), user);

        return users.get(user.getEmail());
    }

    public User update(User user) {

        if (user.getEmail().isEmpty()) {
            throw new RuntimeException("email not exist");
        }

        users.put(user.getEmail(), user);

        return users.get(user.getEmail());
    }


}