package org.example.lab3.mbeans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PointsCounter extends NotificationBroadcasterSupport implements PointsCounterMBean {
    private Integer totalPoints;
    private Integer outsidePoints;
    private long sequenceNumber = 1;

    public void addPoint(boolean isInsideArea) {
        int total = totalPoints++;
        if (!isInsideArea) outsidePoints++;

        if (total % 15 == 0) {
            Notification n = new Notification(
                    "points.counter.multiple15",
                    this,
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "Total points is multiple of 15: " + total
            );
            sendNotification(n);
        }
    }

    @Override
    public int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public int getPointsOutsideArea() {
        return outsidePoints;
    }
}
