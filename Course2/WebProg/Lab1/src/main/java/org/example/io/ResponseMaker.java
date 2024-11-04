package org.example.io;

import org.example.service.Params;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.example.exceptions.ValidationException.statusCode;

public class ResponseMaker {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void makeSuccessResponse(Params params, long execTime, boolean result) {
        String formattedNow = LocalDateTime.now().format(formatter);
        var json = String.format(String.valueOf(ResponseTemplates.RESULT_JSON), params.getX(), params.getY(), params.getR(), execTime, formattedNow, result);
        var response = String.format(String.valueOf(ResponseTemplates.HTTP_RESPONSE), json.getBytes(StandardCharsets.UTF_8).length, json);
        System.out.println(response);
    }

    public static void makeErrorResponse(String erMessage){
        String formattedNow = LocalDateTime.now().format(formatter);
        var json = String.format(String.valueOf(ResponseTemplates.ERROR_JSON), formattedNow, erMessage);
        var response = String.format(String.valueOf(ResponseTemplates.HTTP_ERROR), statusCode, json.getBytes(StandardCharsets.UTF_8).length, json);
        System.out.println(response);
    }

}
