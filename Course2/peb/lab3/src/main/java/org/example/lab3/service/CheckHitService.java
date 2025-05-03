package org.example.lab3.service;

import org.example.lab3.entity.Result;
import org.example.lab3.repository.ResultRepository;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class CheckHitService implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final transient ResultRepository resultRepository = new ResultRepository();

    public boolean checkDot(float x, float y, float r) {
        return (x >= 0 && x < r && y >= 0 && y < r/2) ||
                (x >= 0 && y <= 0 && (x - y) < r/2) ||
                (x <= 0 && y >= 0 && x * x + y * y <= r * r);
    }

    public void saveResult(Result result) {
        resultRepository.save(result);
    }

    public List<Result> findAllResults() {
        return resultRepository.findAll();
    }

    public void clearAllResults() {
        ResultRepository resultRepository = new ResultRepository();
        resultRepository.clean();
    }

}

