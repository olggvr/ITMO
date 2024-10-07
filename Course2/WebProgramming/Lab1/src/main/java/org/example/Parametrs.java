package org.example;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class Parameters {
    private final int x;
    private final float y;
    private final float r;

    // constructor
    public Parameters(String jsonString) throws ValidationException {
        if (jsonString == null || jsonString.isEmpty()) {
            throw new ValidationException(400, "Missing request body");
        }

        try {

            // JSON string parsing
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);  // JSON -> Object
            if (!jsonObject.containsKey("x") || !jsonObject.containsKey("y") || !jsonObject.containsKey("r")) {
                throw new ValidationException(400, "Missing one param or more, json invalid");
            }

            // get values and validate
            this.x = validateX((String) jsonObject.get("x").toString());
            this.y = validateY((String) jsonObject.get("y").toString());
            this.r = validateR((String)jsonObject.get("r").toString());

        } catch (ParseException e) {
            throw new ValidationException(400, "Error parsing json");
        }
    }

    // validation
    private int validateX(String x) throws ValidationException {
        if (x == null || x.isEmpty()) {
            throw new ValidationException(400, "X is empty");
        }
        try {
            int xx = Integer.parseInt(x);
            if (xx < -4 || xx > 4) {
                throw new ValidationException(400, "Wrong X");
            }
            return xx;
        } catch (NumberFormatException e) {
            throw new ValidationException(400, "X is not a number");
        }
    }

    private float validateY(String y) throws ValidationException {
        if (y == null || y.isEmpty()) {
            throw new ValidationException(400, "Y is empty ");
        }
        try {
            float yy = Float.parseFloat(y);
            if (yy < -3 || yy > 5) {
                throw new ValidationException(400, "Wrong Y");
            }
            return yy;
        } catch (NumberFormatException e) {
            throw new ValidationException(400, "Y is not a number");
        }
    }

    private float validateR(String r) throws ValidationException {
        if (r == null || r.isEmpty()) {
            throw new ValidationException(400, "R is empty");
        }
        try {
            float rr = Float.parseFloat(r);
            if (rr < 1 || rr > 3) {
                throw new ValidationException(400, "Wrong R");
            }
            return rr;
        } catch (NumberFormatException e) {
            throw new ValidationException(400, "R is not a number");
        }
    }

    // getters
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