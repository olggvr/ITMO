package org.example.lab3.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.lab3.entity.Result;
import org.example.lab3.repository.ResultRepositoryImpl;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class CheckHitService implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final transient ResultRepositoryImpl resultRepositoryImpl;

    @Inject
    public CheckHitService(ResultRepositoryImpl resultRepositoryImpl) {
        this.resultRepositoryImpl = resultRepositoryImpl;
    }

    /**
     *
     * @param x coordinate
     * @param y coordinate
     * @param r - param, sets scale of available areas
     * @return True - dot placed inside given area, False - dot placed outside given area
     */
    public static boolean checkDot(float x, float y, float r) {
        return (x >= 0 && x < r && y >= 0 && y < r/2) ||
                (x >= 0 && y <= 0 && (x - y) < r/2) ||
                (x <= 0 && y >= 0 && x * x + y * y <= r * r);
    }

    public void saveResult(Result result) {
        resultRepositoryImpl.save(result);
    }

    public List<Result> findAllResults() {
        return resultRepositoryImpl.findAll();
    }

    public void clearAllResults() {
        ResultRepositoryImpl resultRepositoryImpl = new ResultRepositoryImpl();
        resultRepositoryImpl.clean();
    }

}

