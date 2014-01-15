/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizglobal.vehicletracker.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.primefaces.event.SlideEndEvent;

/**
 * Events at homepage are handled here mainly.
 *
 * @author kenny
 */
@Named(value = "dashboardController")
@SessionScoped
public class DashboardController extends BasePage implements Serializable {
    
    private final int defaultReshRate = 2; //2 seconds
    private int refreshRate;

    /**
     * Creates a new instance of DashboardController
     */
    public DashboardController() {
    }

    @Override@PostConstruct
    public void init() {
        refreshRate = defaultReshRate;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }
    
    public void slideEndEvent(SlideEndEvent slideEndEvent) {
        setRefreshRate(slideEndEvent.getValue());
    }
    
}
