package org.example;

import com.fastcgi.FCGIInterface;
import org.example.exceptions.ValidationException;
import org.example.io.Receiver;
import org.example.io.ResponseMaker;
import org.example.service.Calculator;

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

                String header = Receiver.getCustomHeader();

                int contNum = 0;
                if (contNum == params.getX()) {
                    ResponseMaker.makeRedirectResponse("http://localhost:8080/b.html");
                }

                ResponseMaker.makeSuccessResponse(params, execTime, result, header + "_new");
            } catch (ValidationException | IOException e) {
                ResponseMaker.makeErrorResponse(e.getMessage());
            }
        }
    }

}

