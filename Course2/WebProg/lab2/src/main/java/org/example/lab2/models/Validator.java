package org.example.lab2.models;

public class Validator {

    public static boolean validateIsNull(String x, String y, String r){
        return x == null || y == null || r == null;
    }

    public static boolean validateIsEmpty(String x, String y, String r){
        return x.isEmpty() || y.isEmpty() || r.isEmpty();
    }

    public static boolean isCorrectDiapason(int x, double y, double r){
        return (x >= -5 && x <= 3) && (y >= -5 && y <= 5) && (r >= 1 && r <= 3);
    }

}
