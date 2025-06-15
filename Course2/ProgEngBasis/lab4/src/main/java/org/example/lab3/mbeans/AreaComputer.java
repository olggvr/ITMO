package org.example.lab3.mbeans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import javax.management.Notification;
import javax.management.NotificationListener;;

@Named("AreaComputer")
@ApplicationScoped
public class AreaComputer implements AreaComputerMBean, NotificationListener {

    private static double area = 0.0;

    private static double calculateArea(float r) {
        return r * r * (1 + Math.PI / 4);
    }

    @Override
    public void handleNotification(Notification notification, Object handback) {
        float r = 1f;
        area = calculateArea(r);
        System.out.println("Area: " + area);
    }

    @Override
    public double getArea() {
        return area;
    }
}
