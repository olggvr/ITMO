package org.example.lab2.models;

public record Point(int x, double y, double r, boolean result) {

    public Point(int x, double y, double r, boolean result){
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

}
