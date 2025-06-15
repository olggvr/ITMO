package org.example.lab3.mbeans.interfaces;

public interface PointsCounterMBean {
    void onPointAdded(boolean isHit);
    int getTotalPoints();
    int getTotalOutsidePoints();
}
