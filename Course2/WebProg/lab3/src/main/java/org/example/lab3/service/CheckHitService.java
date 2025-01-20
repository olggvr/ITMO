package org.example.lab3.service;

import org.example.lab3.entity.Result;
import org.example.lab3.repository.ResultRepository;

import java.io.Serializable;
import java.util.List;

public class CheckHitService implements Serializable {

    private static final long serialVersionUID = 1L;

    private final transient ResultRepository ResultRepository = new ResultRepository();

    public boolean checkDot(float x, float y, float r) {
        if (x <= 0 && y >= 0 && (y <= (-x/2 - r))) {
            return true;
        } else if (x >= 0 && y <= 0 && x <= r && -y <= r) {
            return true;
        } else if (x < 0 && y < 0) {
            return (x * x + y * y <= r * r);
        }
        return false;
    }

    public void saveResult(Result result) {
        ResultRepository.save(result);
    }

    public List<Result> findAllResults() {
        return ResultRepository.findAll();
    }
}

