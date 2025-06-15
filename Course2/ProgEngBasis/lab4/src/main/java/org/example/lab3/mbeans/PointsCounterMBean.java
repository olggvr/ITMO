package org.example.lab3.mbeans;

public interface PointsCounterMBean {
    void onPointAdded(boolean isHit);
    int getTotalPoints();
    int getTotalOutsidePoints();
}
