package org.example;

import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        var fcgi = new FCGIInterface();

        while (fcgi.FCGIaccept() >= 0) {
            try {

                String requestMethod = System.getProperties().getProperty("REQUEST_METHOD");
                if (!"POST".equals(requestMethod)) {
                    throw new ValidationException(400, "Only POST requests are supported");
                }
                String contentLengthHeader = System.getProperties().getProperty("CONTENT_LENGTH");
                if (contentLengthHeader == null) {
                    throw new ValidationException(400, "Content-Length header is missing");
                }

                var params = Params.getParameters(contentLengthHeader);

                var startTime = Instant.now();
                var result = Calculator.calculate(params.getX(), params.getY(), params.getR());
                var endTime = Instant.now();

                long execTime = Calculator.getDuration(startTime, endTime);

                ResponseMaker.makeSuccessResponse(params, execTime, result);
            } catch (ValidationException | IOException e) {
                ResponseMaker.makeErrorResponse(e.getMessage());
            }
        }
    }
}

