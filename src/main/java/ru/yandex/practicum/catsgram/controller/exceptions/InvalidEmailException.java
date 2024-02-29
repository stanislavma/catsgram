package ru.yandex.practicum.catsgram.controller.exceptions;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String message) {
        super(message);
    }
}
