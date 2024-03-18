package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.catsgram.controller.exceptions.*;
import ru.yandex.practicum.catsgram.model.ErrorResponse;

@RestControllerAdvice("ru.yandex.practicum.catsgram")
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle1(final PostNotFoundException e) {
        return new ErrorResponse(
                "error", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle2(final UserNotFoundException e) {
        return new ErrorResponse(
                "error", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handle3(final UserAlreadyExistException e) {
        return new ErrorResponse(
                "error", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle4(final InvalidEmailException e) {
        return new ErrorResponse(
                "error", e.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle5(final IncorrectParameterException e) {
        return new ErrorResponse(
                "error", "Ошибка с полем " + e.getParameter()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle6(final Throwable e) {
        return new ErrorResponse(
                "error", "Произошла непредвиденная ошибка."
        );
    }

} 