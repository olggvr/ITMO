package org.example.lab3.mbeans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@Named("ConfigMBeans")
@ApplicationScoped
public class ConfigBean {

    private PointsCounter pointsCounter;

    @PostConstruct
    public void init() {
        try {
            pointsCounter = new PointsCounter();
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("org.example.lab3.mbeans:type=PointsCounter");
            if (!mbs.isRegistered(name)) {
                mbs.registerMBean(pointsCounter, name);
            }

            CalculateArea calculateArea = new CalculateArea();
            ObjectName areaName = new ObjectName("org.example.lab3.mbeans:type=CalculateArea");
            if (!mbs.isRegistered(areaName)) {
                mbs.registerMBean(calculateArea, areaName);
            }

            pointsCounter.addNotificationListener(calculateArea, null, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public PointsCounter getPointsCounter() {
        return pointsCounter;
    }

}
