package org.example;

import com.fastcgi.FCGIInterface;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {
    private static final String HTTP_RESPONSE = """
            HTTP/1.1 200 OK
            Content-Type: application/json
            Content-Length: %d
            
            %s
            """;
    private static final String HTTP_ERROR = """
            HTTP/1.1 400 Bad Request
            Content-Type: application/json
            Content-Length: %d
            
            %s
            """;
    private static final String RESULT_JSON = """
            {
                "time": "%s",
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
        while (fcgi.FCGIaccept() >= 0) {
            try {
                var queryParams = System.getProperties().getProperty("QUERY_STRING");
                var params = new ParamsGET(queryParams);

                var startTime = Instant.now();
                var result = validate(params.getX(), params.getY(), params.getR());
                var endTime = Instant.now();

                var json = String.format(RESULT_JSON, ChronoUnit.NANOS.between(startTime, endTime), LocalDateTime.now(), result);
                var response = String.format(HTTP_RESPONSE, json.getBytes(StandardCharsets.UTF_8).length + 2, json);
                System.out.println(response);
            } catch (ValidateException e) {
                var json = String.format(ERROR_JSON, LocalDateTime.now(), e.getMessage());
                var response = String.format(HTTP_ERROR, json.getBytes(StandardCharsets.UTF_8).length + 2, json);
                System.out.println(response);
            }
        }
    }

    private static boolean validate (float x, float y, float r){
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

