package org.example.lab3.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.example.lab3.entity.Result;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("checkHitService")
@ApplicationScoped
public class CheckHitService implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Result> results = new ArrayList<>();

    public boolean checkDot(float x, float y, float r) {
        return (x >= 0 && x < r && y >= 0 && y < r/2) ||
                (x >= 0 && y <= 0 && (x - y) < r/2) ||
                (x <= 0 && y >= 0 && x * x + y * y <= r * r);
    }

    public void saveResult(Result result) {
        results.add(result);
    }

    public List<Result> findAllResults() {
        return results;
    }

    public void clearAllResults() {
        results.clear();
    }

}

