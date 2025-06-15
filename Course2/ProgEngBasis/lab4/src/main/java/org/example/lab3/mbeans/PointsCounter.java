package org.example.lab3.mbeans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PointsCounter extends NotificationBroadcasterSupport implements PointsCounterMBean {

    public static int totalPoints = 0;
    public static int totalOutsidePoints = 0;

    private int sequenceNumber = 0;

    @Override
    public void onPointAdded(boolean isHit) {
        if (isHit) totalOutsidePoints++;

        if (totalPoints++ % 15 == 0) {
            System.out.println("Points: " + totalPoints);

            Notification notification = new Notification(
                    "lab3.totalPointsIncreased",
                    this,
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "Total points value increased and now divides by 15"
            );
            sendNotification(notification);
        }
    }
}
