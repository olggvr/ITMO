package org.example.lab3.mbeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import javax.management.*;
import java.lang.management.ManagementFactory;

@Named("pointsCounter")
@ApplicationScoped
public class PointsCounter extends NotificationBroadcasterSupport implements PointsCounterMBean {

    private static int totalPoints = 0;
    private static int totalOutsidePoints = 0;

    private int sequenceNumber = 0;

    @PostConstruct
    private void registerMBeans() {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("org.example.lab3.mbeans:type=PointsCounter");
            if (!server.isRegistered(objectName)) {
                server.registerMBean(this, objectName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onPointAdded(boolean isHit) {
        if (isHit) totalOutsidePoints++;
        totalPoints++;
        if (totalPoints % 15 == 0) {
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

    @Override
    public int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public int getTotalOutsidePoints() {
        return totalOutsidePoints;
    }
}
