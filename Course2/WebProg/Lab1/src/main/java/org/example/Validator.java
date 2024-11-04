package org.example;

public class Validator {

    public static int validateX(int x) throws ValidationException {
        if (x < -4 || x > 4) {
            throw new ValidationException(400, "Wrong X");
        }
        return x;
    }

    public static float validateY(float y) throws ValidationException {
        if (y < -3 || y > 5 || Float.isNaN(y)) {
            throw new ValidationException(400, "Wrong Y");
        }
        return y;
    }

    public static float validateR(float r) throws ValidationException {
        if (r < 1 || r > 3 || Float.isNaN(r)) {
            throw new ValidationException(400, "Wrong R");
        }
        return r;
    }

    public static void validateRequestHeader(String contentLengthHeader) throws ValidationException {
        if (contentLengthHeader == null){
            throw new ValidationException(400, "Content-Length header is missing");
        }
    }

    public static void validateRequestMethod() throws ValidationException {
        String requestMethod = System.getProperties().getProperty("REQUEST_METHOD");
        if (!"POST".equals(requestMethod)) {
            throw new ValidationException(400, "Only POST requests are supported");
        }
    }

}
