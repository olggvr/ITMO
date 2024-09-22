package org.example;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

class Params {

    private static int x;
    private static float y;
    private static float r;

    public Params(String body) throws ValidateException{
        try{
            if (body.isEmpty()) throw new ValidateException("Missing query string");
            Map<String, String> params = parseFormData(body);
            x = Integer.parseInt(params.get("x"));
            y = Float.parseFloat(params.get("y"));
            r = Float.parseFloat(params.get("r"));
        }catch (UnsupportedEncodingException e){
            return ;
        }
    }

    private static Map<String, String> parseFormData(String body) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();

        String[] pairs = body.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8.name());
            String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
            params.put(key, value);
        }
        return params;
    }

    public int getX() {return x;}

    public float getY() {return y;}

    public float getR() {return r;}
}
