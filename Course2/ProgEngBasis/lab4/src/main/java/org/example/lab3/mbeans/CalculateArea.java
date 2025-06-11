package org.example.lab3.mbeans;

import javax.management.Notification;
import javax.management.NotificationListener;

public class CalculateArea implements CalculateAreaMBean, NotificationListener {
    private volatile double area = 0.0;

    @Override
    public double calculateArea() {
        return 0;
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        if ("points.counter.multiple15".equals(notification.getType())) {
            calculateArea();
        }
    }

    // + setPoints(), setPointSource(), updateArea(points) — методы для получения точек (см. ниже)
}
