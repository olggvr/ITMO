package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Params {
    private final int x;
    private final float y;
    private final float r;

    public Params(int x, float y, float r) throws ValidationException {
        this.x = validateX(x);
        this.y = validateY(y);
        this.r = validateR(r);
    }

    public static String getRequestBodyStr(String contentLengthHeader) throws ValidationException, IOException {
        int contentLength = Integer.parseInt(contentLengthHeader);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        char[] bodyChars = new char[contentLength];
        int charsRead = reader.read(bodyChars, 0, contentLength);

        if (charsRead < contentLength) {
            throw new ValidationException(400, "Bad request body");
        }

        return new String(bodyChars);
    }

    public static Params parseRequestBody(String body) throws ValidationException {
        Gson gson = new Gson();
        try{
            JsonParams jsonParams = gson.fromJson(body, JsonParams.class);
            return new Params(jsonParams.x(), jsonParams.y(), jsonParams.r());
        } catch (JsonSyntaxException e) {
            throw new ValidationException(400, "Bad request body");
        }
    }

    private int validateX(int x) throws ValidationException {
        if (x < -4 || x > 4) {
            throw new ValidationException(400, "Wrong X");
        }
        return x;
    }

    private float validateY(float y) throws ValidationException {
        if (y < -3 || y > 5 || Float.isNaN(y)) {
            throw new ValidationException(400, "Wrong Y");
        }
        return y;
    }

    private float validateR(float r) throws ValidationException {
        if (r < 1 || r > 3 || Float.isNaN(r)) {
            throw new ValidationException(400, "Wrong R");
        }
        return r;
    }

    public int getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    private static class JsonParams {

        private int x;
        private float y;
        private float r;



    }
}
