package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Receiver {

    public static Params parseRequestBody(String body) throws ValidationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonParams jsonParams = objectMapper.readValue(body, JsonParams.class);
            return new Params(jsonParams.x(), jsonParams.y(), jsonParams.r());
        } catch (JsonProcessingException e) {
            throw new ValidationException(400, "Bad request body");
        }
    }

    public static String getRequestBodyStr() throws ValidationException, IOException {
        String contentLengthHeader = System.getProperties().getProperty("CONTENT_LENGTH");
        Validator.validateRequestHeader(contentLengthHeader);
        Validator.validateRequestMethod();

        int contentLength = Integer.parseInt(contentLengthHeader);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        char[] bodyChars = new char[contentLength];
        int charsRead = reader.read(bodyChars, 0, contentLength);

        if (charsRead < contentLength) {
            throw new ValidationException(400, "Bad request body");
        }

        return new String(bodyChars);
    }

}