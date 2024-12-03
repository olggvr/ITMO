package org.example.lab2.models;

import java.util.HashSet;
import java.util.Set;

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

    public static boolean isCorrectR(double r){
        Set<Double> allowed = new HashSet<Double>();
        allowed.add(1.0);
        allowed.add(1.5);
        allowed.add(2.0);
        allowed.add(2.5);
        allowed.add(3.0);
        return allowed.contains(r);
    }
}
