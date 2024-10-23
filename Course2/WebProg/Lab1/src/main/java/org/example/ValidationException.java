package org.example;

public class ValidationException extends Exception {

    static int statusCode;

    public ValidationException(int statusCode, String message) {
        super(message);
        ValidationException.statusCode = statusCode;
    }
}
