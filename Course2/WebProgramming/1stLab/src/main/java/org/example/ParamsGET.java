package org.example;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class ParamsGET {
    private final int x;
    private final float y;
    private final float r;

    public ParamsGET(String query) throws ValidateException {
        if (query == null || query.isEmpty()) {
            throw new ValidateException("Missing query string");
        }
        var params = splitQuery(query);
        validateParams(params);
        this.x = Integer.parseInt(params.get("x"));
        this.y = Float.parseFloat(params.get("y"));
        this.r = Float.parseFloat(params.get("r"));
    }

    private static Map<String, String> splitQuery(String query) {
        return Arrays.stream(query.split("&"))
                .map(pair -> pair.split("="))
                .collect(
                        Collectors.toMap(
                                pairParts -> URLDecoder.decode(pairParts[0], StandardCharsets.UTF_8),
                                pairParts -> URLDecoder.decode(pairParts[1], StandardCharsets.UTF_8),
                                (a, b) -> b,
                                HashMap::new
                        )
                );
    }


    private static void validateParams(Map<String, String> params) throws ValidateException {
        var x = params.get("x");
        if (x == null || x.isEmpty()) {
            throw new ValidateException("x is invalid");
        }

        try {
            var xx = Integer.parseInt(x);
            if (xx < -3 || xx > 5) {
                throw new ValidateException("x has forbidden value");
            }
        } catch (NumberFormatException e) {
            throw new ValidateException("x is not a number");
        }

        var y = params.get("y");
        if (y == null || y.isEmpty()) {
            throw new ValidateException("y is invalid");
        }

        try {
            var yy = Float.parseFloat(y);
            if (yy < -3 || yy > 5) {
                throw new ValidateException("y has forbidden value");
            }
        } catch (NumberFormatException e) {
            throw new ValidateException("y is not a number");
        }

        var r = params.get("r");
        if (r == null || r.isEmpty()) {
            throw new ValidateException("r is invalid");
        }

        try {
            var rr = Float.parseFloat(r);
            if (rr < 1 || rr > 3) {
                throw new ValidateException("r has forbidden value");
            }
        } catch (NumberFormatException e) {
            throw new ValidateException("r is not a number");
        }
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
}