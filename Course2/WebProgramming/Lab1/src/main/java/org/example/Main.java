package org.example;

import com.fastcgi.FCGIInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.example.ValidationException.statusCode;

public class Main {

    private static final String HTTP_RESPONSE = """
    Status: 200 OK
    Content-Type: application/json;charset=utf-8
    Content-Length: %d
    
    %s
    """;
    private static final String HTTP_ERROR = """
    Status: %d
    Content-Type: application/json;charset=utf-8
    Content-Length: %d

    %s
    """;
    private static final String RESULT_JSON = """
    {
        "x": "%d",
        "y": "%f",
        "z": "%f",
        "time": "%s ns",
        "now": "%s",
        "result": %b
    }
    """;
    private static final String ERROR_JSON = """
    {
        "now": "%s",
        "reason": "%s"
    }
    """;

    public static void main(String[] args) {
        var fcgi = new FCGIInterface();

        // time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        while (fcgi.FCGIaccept() >= 0) {
            try {

                // http method verification
                String requestMethod = System.getProperties().getProperty("REQUEST_METHOD");
                if (!"POST".equals(requestMethod)) {
                    throw new ValidationException(400, "Only POST requests are supported");
                }

                // get Content-Length
                String contentLengthHeader = System.getProperties().getProperty("CONTENT_LENGTH");
                var params = getParameters(contentLengthHeader);

                // check values and time
                var startTime = Instant.now();
                var result = calculate(params.getX(), params.getY(), params.getR());
                var endTime = Instant.now();

                // formatting time
                long timeTakenNanos = ChronoUnit.NANOS.between(startTime, endTime);
                String formattedNow = LocalDateTime.now().format(formatter);

                // make success json response
                var json = String.format(RESULT_JSON, params.getX(), params.getY(), params.getR(), timeTakenNanos, formattedNow, result);
                var response = String.format(HTTP_RESPONSE, json.getBytes(StandardCharsets.UTF_8).length, json);
                System.out.println(response);
            } catch (ValidationException | IOException e) {

                // make error response
                var formattedNow = LocalDateTime.now().format(formatter);
                var json = String.format(ERROR_JSON, formattedNow, e.getMessage());
                var response = String.format(HTTP_ERROR, statusCode, json.getBytes(StandardCharsets.UTF_8).length, json);
                System.out.println(response);
            }
        }
    }

    private static Params getParameters(String contentLengthHeader) throws ValidationException, IOException {
        if (contentLengthHeader == null) {
            throw new ValidationException(400, "Content-Length header missing");
        }

        // read body to buffer
        int contentLength = Integer.parseInt(contentLengthHeader);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        char[] bodyChars = new char[contentLength];
        reader.read(bodyChars, 0, contentLength);

        String requestBody = new String(bodyChars);
        return new Params(requestBody);
    }

    // calculate values function
    private static boolean calculate (float x, float y, float r){
        if (x < 0 && y > 0) return false;
        if (x > 0 && y > 0)
            if (x > r/2 || y > r) return false;
        if (x > 0 && y < 0)
            if (x + y/2 > r/2) return false;
        if (x < 0 && y < 0)
            if (x*x + y*y > r*r) return false;
        return true;
    }
}

