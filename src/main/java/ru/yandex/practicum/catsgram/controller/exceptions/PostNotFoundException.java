package ru.yandex.practicum.catsgram.controller.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message){
        super(message);
    }
}