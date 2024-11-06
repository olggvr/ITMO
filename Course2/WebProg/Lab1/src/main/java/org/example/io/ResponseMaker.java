package org.example.io;

import org.example.service.Params;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.example.exceptions.ValidationException.statusCode;

public class ResponseMaker {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static final String HTTP_RESPONSE = """
        Status: 200 OK
        Content-Type: application/json;charset=utf-8
        Content-Length: %d
        Custom-Header: %s
        
        %s
        """;
    private static final String HTTP_ERROR ="""
        Status: %d
        Content-Type: application/json;charset=utf-8
        Content-Length: %d
    
        %s
        """;
    private static final String HTTP_REDIRECT = """
            Status: 302 Moved Permanently
            Content-Type: text/html;charset=utf-8
            Location: %s
            """;
    private static final String RESULT_JSON = """
        {
            "x": "%d",
            "y": "%f",
            "r": "%f",
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

    public static void makeSuccessResponse(Params params, long execTime, boolean result, String header) {
        String formattedNow = LocalDateTime.now().format(formatter);
        var json = String.format(RESULT_JSON, params.getX(), params.getY(), params.getR(), execTime, formattedNow, result);
        var response = String.format(HTTP_RESPONSE,json.getBytes(StandardCharsets.UTF_8).length, header, json);
        System.out.println(response);
    }

    public static void makeErrorResponse(String erMessage){
        String formattedNow = LocalDateTime.now().format(formatter);
        var json = String.format(ERROR_JSON, formattedNow, erMessage);
        var response = String.format(HTTP_ERROR, statusCode, json.getBytes(StandardCharsets.UTF_8).length, json);
        System.out.println(response);
    }

    public static void makeRedirectResponse(String redirectUrl) {
        var response = String.format(HTTP_REDIRECT, redirectUrl);
        System.out.println(response);
    }

}
