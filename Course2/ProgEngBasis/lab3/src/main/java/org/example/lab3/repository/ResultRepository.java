package org.example.lab3.repository;

import org.example.lab3.entity.Result;
import java.util.List;

public interface ResultRepository {
    void save(Result result);
    List<Result> findAll();
    void clean();
}

