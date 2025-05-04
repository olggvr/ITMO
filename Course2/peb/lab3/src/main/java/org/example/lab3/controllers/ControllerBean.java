package org.example.lab3.controllers;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.lab3.service.CheckHitService;
import org.example.lab3.entity.Result;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Bean controller
 * Provides single method of making request, get response from service and save it in db
 *
 * @author popa
 * @version 0.0.2
 * @since 2025-05-04
 */
@Named("controllerBean")
@ApplicationScoped
@ManagedBean
public class ControllerBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final transient CheckHitService checkHitService;

    @Inject
    public ControllerBean(CheckHitService checkHitService) {
        this.checkHitService = checkHitService;
    }

    private float x;
    private float y;
    private float r;

    public void completeRequest() {
        boolean isHit = checkHitService.checkDot(x, y, r);
        Result result = new Result(x, y, r, isHit);
        checkHitService.saveResult(result);
    }

    public void clearResults() {checkHitService.clearAllResults();}

    public List<Result> getResultList() {
        return checkHitService.findAllResults();
    }
}

