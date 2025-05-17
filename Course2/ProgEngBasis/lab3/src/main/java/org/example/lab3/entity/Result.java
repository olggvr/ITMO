package org.example.lab3.entity;

import jakarta.persistence.*;

@Entity
@Table()
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float x;
    private float y;
    private float r;
    private boolean result;

    public Result(float x, float y, float r, boolean result) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }
    public Result() {}

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", result=" + result +
                '}';
    }
}

