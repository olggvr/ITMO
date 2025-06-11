package org.example.lab3.controllers;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.lab3.mbeans.ConfigBean;
import org.example.lab3.service.CheckHitService;
import org.example.lab3.entity.Result;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Named("controllerBean")
@ApplicationScoped
@ManagedBean
public class ControllerBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final transient CheckHitService checkHitService = new CheckHitService();

    @Inject
    private ConfigBean configBean;

    private float x;
    private float y;
    private float r;

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

    public void completeRequest() {
        boolean isHit = checkHitService.checkDot(x, y, r);
        Result result = new Result(x, y, r, isHit);
        checkHitService.saveResult(result);

        // call MBean method
        configBean.getPointsCounter().addPoint(isHit);
    }

    public void clearResults() {checkHitService.clearAllResults();}

    public List<Result> getResultList() {
        return checkHitService.findAllResults();
    }
}

