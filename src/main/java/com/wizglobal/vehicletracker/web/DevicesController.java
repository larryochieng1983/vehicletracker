package com.wizglobal.vehicletracker.web;

import com.wizglobal.vehicletracker.domain.GpsDevice;
import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.service.GpsDeviceService;
import com.wizglobal.vehicletracker.service.VehicleService;
import com.wizglobal.vehicletracker.util.StringUtils;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author kenny
 */
@Named(value = "devicesController")
@SessionScoped
public class DevicesController extends BasePage implements Serializable {
private static final org.apache.log4j.Logger LOGGER = Logger
			.getLogger( DevicesController.class );
    @Inject
    private GpsDeviceService gpsDeviceService;
    @Inject
    private VehicleService vehicleService;
    private Vehicle currentVehicle;
    private GpsDevice currentDevice;
    private LazyDataModel<GpsDevice> gpsDevicesModel;
    private List<Vehicle> availableVehicles;
    private boolean configuringNew = false;
    
    public void preRenderView(){
        if (getRequestParams().containsKey("new") || currentDevice == null ) {
            currentDevice = new GpsDevice();
            configuringNew = true;
        } else {
            configuringNew = false;
        }
    }

    @PostConstruct
    @Override
    public void init() {
        availableVehicles = new ArrayList<>();
    }

    /**
     * Creates a new instance of DevicesController
     */
    public DevicesController() {
    }

    public String showAllDevices() {
        return appendFacesRedirectTrue("/devices/list.jsf");
    }
    
    /**
     * Prepare to configure an existing device.
     *
     * @return null if no device selected or /devices/configure.jsf 
     */
    public String configureDevice() {
        GpsDevice selectedDevice = getGpsDevicesModel().getRowData();
        if (selectedDevice == null ) {
            addWarningMessage("Please select a device from the list to configure.", null);
            return null;
        } else {
            //search vehicle associated with this device
            setCurrentDevice(selectedDevice);
            List<Vehicle> targetVehicle = vehicleService.findWithNamedQuery("Vehicle.findVehicleByDevice", Collections.singletonMap("gpsDevice", selectedDevice));
            setCurrentVehicle(targetVehicle.isEmpty() ? null : targetVehicle.get(0));
            return appendFacesRedirectTrue("/devices/configure.jsf");
        }
    }
    
    public String saveDeviceConfiguration() {
        if (currentVehicle != null && isValid(currentDevice)) {
            try {
                currentVehicle.setGpsDevice( configuringNew ? gpsDeviceService.create(currentDevice) : gpsDeviceService.update(currentDevice));
                vehicleService.update(currentVehicle);
                addInfoMessage("Device configured for " + currentVehicle.getRegistrationNumber(), null);
            } catch (Exception e) {
                LOGGER.warn("Error saving new device. " + e.getMessage());
                addErrorgMessage("Falied to save new device. Please check your input and try again later.", e.getMessage());
            }
        }
        return appendFacesRedirectTrue("/devices/list.jsf");
    }
    
    public void deleteDevice() {
        GpsDevice selectedDevice = getGpsDevicesModel().getRowData();
        if (selectedDevice == null ) {
            addWarningMessage("Please select a device from the list to delete.", null);
        } else {
            gpsDeviceService.delete(selectedDevice.getId());
            addInfoMessage("Device removed successfuly", null);
        }
    }
    
    /**
     * Validates for availability of phonenumber
     *
     * @param gpsDevice device to validate
     * @return true iff atleast phonenumber is specified.
     */
    protected boolean isValid(GpsDevice gpsDevice){
        if (gpsDevice == null || !StringUtils.isNonEmptyString(gpsDevice.getPhoneNumber())) {
            return false;
        } 
        return true;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public GpsDevice getCurrentDevice() {
        return currentDevice;
    }

    public void setCurrentDevice(GpsDevice currentDevice) {
        this.currentDevice = currentDevice;
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

    public List<Vehicle> getAvailableVehicles() {
        return availableVehicles;
    }

    public String onWizardFlow(FlowEvent event) {
        if ("add_to_vehicle".equalsIgnoreCase(event.getNewStep())) { //reload vehicles to display
            availableVehicles = vehicleService.findWithNamedQuery("Vehicle.findAll");
        }
        return event.getNewStep();
    }
}
