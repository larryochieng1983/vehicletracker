package com.wizglobal.vehicletracker.web;

import com.wizglobal.vehicletracker.domain.GpsDevice;
import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.service.GpsDeviceService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author kenny
 */
@Named(value = "devicesController")
@SessionScoped
public class DevicesController extends BasePage implements Serializable {

    @Inject
    private GpsDeviceService gpsDeviceService;
    private Vehicle currentVehicle;
    private LazyDataModel<GpsDevice> gpsDevicesModel;

    @PostConstruct
    @Override
    public void init() {
    }

    /**
     * Creates a new instance of DevicesController
     */
    public DevicesController() {
    }

    public String showAllDevices() {
        return appendFacesRedirectTrue("/devices/list.jsf");
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }

    public LazyDataModel<GpsDevice> getGpsDevicesModel() {
        if (gpsDevicesModel == null) {
            gpsDevicesModel = new LazyGpsDeviceDataModel(gpsDeviceService);
        }
        return gpsDevicesModel;
    }

    public void setGpsDivicesDataModel(LazyGpsDeviceDataModel gpsDivicesDataModel) {
        this.gpsDevicesModel = gpsDivicesDataModel;
    }

    public String onWizardFlow(FlowEvent event) {
        System.out.println("flow event recorded. Previous step " + event.getOldStep());
        System.out.println("next step: " + event.getNewStep());
        return event.getNewStep();
    }
}
