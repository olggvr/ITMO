package org.example.exceptions;

public class ValidationException extends Exception {

    public static int statusCode;

    public ValidationException(int statusCode, String message) {
        super(message);
        ValidationException.statusCode = statusCode;
    }
}
