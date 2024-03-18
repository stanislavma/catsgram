package ru.yandex.practicum.catsgram.controller.exceptions;

public class IncorrectParameterException extends RuntimeException {
    String parameter;

    public IncorrectParameterException(String message, String parameter) {
        super(message);
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}