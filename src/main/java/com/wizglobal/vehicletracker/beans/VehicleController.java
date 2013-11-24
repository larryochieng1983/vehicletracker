package com.wizglobal.vehicletracker.beans;

import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.service.VehicleService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author kenny
 */
@Named(value = "vehicleController")
@SessionScoped
public class VehicleController extends BaseFacesController implements Serializable {

    @Inject
    private VehicleService vehicleService;
    @Inject
    private CustomerController customerController;
    private Vehicle currentVehicle;
    private LazyVehicleTableModel vehicleTableModel;

    @Override@PostConstruct
    public void init() {
	
    }

    /**
     * Creates a new instance of VehicleController
     */
    public VehicleController() {
    }

    public LazyDataModel<Vehicle> getVehicles() {
	return vehicleTableModel;
    }

    public Vehicle getCurrentVehicle() {
	return currentVehicle;
    }

    /**
     *
     * @param currentVehicle set current vehicle.
     */
    public void setCurrentVehicle(Vehicle currentVehicle) {
	this.currentVehicle = currentVehicle;
    }

    /**
     *
     * @return prepare to view customer for current vehicle in /customers/view.
     */
    public String viewSelectedvehicleCustomer() {
	Vehicle vehicle = vehicleTableModel.getRowData();
	if (vehicle != null) {
	    customerController.setCurrectCustomer(vehicle.getCustomer());
	    return "/customers/view?faces-redirect=true";
	}
	return null;
    }

    /**
     *
     * @return navigate to vehicle details page.
     */
    public String viewSelectedVehicle() {
	currentVehicle = vehicleTableModel.getRowData();
	return currentVehicle == null ? null : "/vehicles/view.jsf?faces-redirect=true";
    }
    
    /**
     *
     * @return remove current vehicle and go back to vehicle list.
     */
    public String deleteCurrentVehicle() {
	if (currentVehicle != null) {
	    vehicleService.delete(currentVehicle.getId());
	    return "/vehicles/list.jsf?faces-redirect=true";
	}
	return null;
    }
    
    /**
     *
     * @return Prepare to edit current vehicle.
     */
    public String editVehicle(){
	if (currentVehicle != null) {
	    return "/vehicles/edit.jsf?faces-redirect=true";
	}
	return null;
    }
    
    /**
     *
     * @return Save editing changes for current vehicle.
     */
    public String saveCurrentVehicle(){
	vehicleService.update(currentVehicle);
	return null;
    }

    public static class LazyVehicleTableModel extends LazyDataModel<Vehicle> {
    }
}
