package org.example.service;

import org.example.exceptions.ValidationException;

public class Params {
    private final int x;
    private final float y;
    private final float r;

    public Params(int x, float y, float r) throws ValidationException {
        this.x = Validator.validateX(x);
        this.y = Validator.validateY(y);
        this.r = Validator.validateR(r);
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
