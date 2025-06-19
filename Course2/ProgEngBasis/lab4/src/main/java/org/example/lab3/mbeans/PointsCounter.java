package org.example.lab3.mbeans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import javax.management.*;

@Named("PointsCounter")
@ApplicationScoped
public class PointsCounter extends NotificationBroadcasterSupport implements PointsCounterMBean {

    private static int totalPoints = 0;
    private static int totalOutsidePoints = 0;

    private int sequenceNumber = 0;

    @Override
    public void onPointAdded(boolean isHit) {
        if (!isHit) totalOutsidePoints++;
        totalPoints++;
        if (totalPoints % 15 == 0) {
            System.out.println("Points: " + totalPoints);

            Notification notification = new Notification(
                    "lab3.totalPointsIncreased",
                    "PointsCounter",
                    sequenceNumber++,
                    System.currentTimeMillis(),
                    "Total points value increased and now divides by 15"
            );
            sendNotification(notification);
        }
    }

    @Override
    public int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public int getTotalOutsidePoints() {
        return totalOutsidePoints;
    }
}
