package org.example.lab3.controllers;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.lab3.mbeans.AreaComputer;
import org.example.lab3.mbeans.PointsCounter;
import org.example.lab3.service.CheckHitService;
import org.example.lab3.entity.Result;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.Serial;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.List;

@Named("controllerBean")
@ApplicationScoped
public class ControllerBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private PointsCounter pointsCounter;

    @Inject
    private transient CheckHitService checkHitService;

    private float x;
    private float y;
    private float r;

    @PostConstruct
    private void registerMBeans() {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

            ObjectName pointsCounterName = new ObjectName("org.example.lab3:type=PointsCounter");
            ObjectName areaComputerName = new ObjectName("org.example.lab3:type=AreaComputer");

            if (!mbs.isRegistered(pointsCounterName)) {
                mbs.registerMBean(pointsCounter, pointsCounterName);
            }

            AreaComputer areaComputer = new AreaComputer();
            if (!mbs.isRegistered(areaComputerName)) {
                mbs.registerMBean(areaComputer, areaComputerName);
            }

            mbs.addNotificationListener(pointsCounterName, areaComputer, null, null);

        } catch (Exception e) {
            System.out.println("Error while register: " + e.getMessage());
        }
    }

    public void completeRequest() {
        boolean isHit = checkHitService.checkDot(x, y, r);
        Result result = new Result(x, y, r, isHit);
        checkHitService.saveResult(result);

        pointsCounter.onPointAdded(isHit);
    }

    public void clearResults() {checkHitService.clearAllResults();}

    public List<Result> getResultList() {
        return checkHitService.findAllResults();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
}

