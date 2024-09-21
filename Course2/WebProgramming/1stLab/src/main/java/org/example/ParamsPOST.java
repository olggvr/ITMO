package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ParamsPOST {

    private final int x;
    private final float y;
    private final float r;

    public ParamsPOST(String query) throws ValidateException{
        if (query == null || query.isEmpty()){
            throw new ValidateException("Query params are missing");
        }

        var params = parseQueryString(query);
        this.x = Integer.parseInt(params.get("x"));
        this.y = Float.parseFloat(params.get("y"));
        this.r = Float.parseFloat(params.get("r"));

    }

    private static Map<String, String> parseQueryString(String queryString) {
        Map<String, String> params = new HashMap<>();
        if (queryString == null || queryString.isEmpty()) {
            return params;
        }

        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf('=');
            if (idx > 0) {
                String key = decodeURIComponent(pair.substring(0, idx));
                String value = decodeURIComponent(pair.substring(idx + 1));
                params.put(key, value);
            }
        }
        return params;

    }

    private static String decodeURIComponent(String encodedURIComponent) {
        try {
            return java.net.URLDecoder.decode(encodedURIComponent, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            throw new RuntimeException("Error decoding URL component", e);
        }
    }

}
