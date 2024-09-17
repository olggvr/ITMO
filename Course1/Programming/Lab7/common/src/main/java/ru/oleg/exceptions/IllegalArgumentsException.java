package ru.oleg.exceptions;

import java.io.IOException;


/**
 * Класс для неверных аргументов команды
 */
public class IllegalArgumentsException extends IOException {
    public IllegalArgumentsException() {
    }

    public IllegalArgumentsException(String message) {
        super(message);
    }

    public IllegalArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentsException(Throwable cause) {
        super(cause);
    }
}
