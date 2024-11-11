package org.example.lab2.repository;

import jakarta.ejb.Stateful;
import jakarta.enterprise.context.SessionScoped;
import org.example.lab2.models.Point;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
@SessionScoped
public class PointsRepository implements Serializable {

    private List<Point> points = new ArrayList<>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getPoints() {
        return points;
    }

}
