package org.example;

import java.time.Duration;
import java.time.Instant;

public class Calculator {

    public static boolean calculate (float x, float y, float r){
        if (x < 0 && y > 0) return false;
        if (x > 0 && y > 0)
            if (x > r/2 || y > r) return false;
        if (x > 0 && y < 0)
            if (x + y/2 > r/2) return false;
        if (x < 0 && y < 0)
            return !(x * x + y * y > r * r);
        return true;
    }

    public static long getDuration(Instant startTime, Instant endTime){
        return Duration.between(startTime, endTime).toNanos();
    }

}
