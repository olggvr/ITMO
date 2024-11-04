package org.example;

import com.fastcgi.FCGIInterface;

import java.io.IOException;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        var fcgi = new FCGIInterface();

        while (fcgi.FCGIaccept() >= 0) {
            try {
                var reqBody = Receiver.getRequestBodyStr();
                var params = Receiver.parseRequestBody(reqBody);

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

