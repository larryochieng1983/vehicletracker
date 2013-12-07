package com.wizglobal.vehicletracker.beans;

import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.domain.VehicleColor;
import com.wizglobal.vehicletracker.domain.VehicleColorService;
import com.wizglobal.vehicletracker.domain.VehicleModel;
import com.wizglobal.vehicletracker.exception.DataAccessException;
import com.wizglobal.vehicletracker.service.VehicleManufacturerService;
import com.wizglobal.vehicletracker.service.VehicleModelService;
import com.wizglobal.vehicletracker.service.VehicleService;
import com.wizglobal.vehicletracker.service.VehicleTypeService;
import com.wizglobal.vehicletracker.util.LazySorter;
import com.wizglobal.vehicletracker.util.QueryParam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kenny
 */
@Named(value = "vehicleController")
@SessionScoped
public class VehicleController extends BasePage implements Serializable {
    
    private static final Logger LOG = Logger.getLogger(VehicleController.class);

    @Inject
    private VehicleService vehicleService;
    @Inject
    private CustomerController customerController;
    @Inject
    private VehicleColorService vehicleColorService;
    @Inject
    private VehicleManufacturerService manufacturerService;
    @Inject 
    private VehicleModelService modelService;
    @Inject 
    private VehicleTypeService vehicleTypeService;
    
    
    private Vehicle currentVehicle;
    private Vehicle newVehicle;
    private LazyVehicleTableModel vehicleTableModel;
    
    public void preRenderPrepareNewVehicle(){
	if (!isAjaxRequest() && currentVehicle == null) {
	    currentVehicle = new Vehicle();
	}
    }

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

    public Vehicle getNewVehicle() {
	return newVehicle;
    }

    public void setNewVehicle(Vehicle newVehicle) {
	this.newVehicle = newVehicle;
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
	    customerController.setCurrentCustomer(vehicle.getCustomer());
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
     * @return prepare to add a new vehicle.
     */
    public String createNewVehicle(){
	newVehicle = null;
	return appendFacesRedirectTrue("/vehicles/new.jsf");
    }
    
    /**
     *
     * @return Performs actual database ADD.
     */
    public String addNewVehicle(){
	try {
	    currentVehicle = vehicleService.create(newVehicle);
	    appendFacesRedirectTrue("/vehicles/view.jsf");
	} catch (DataAccessException ex) {
	    addErrorgMessage("Unable to create new vehicle. Please try again.", ex.getMessage());
	    java.util.logging.Logger.getLogger(VehicleController.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }
    
    /**
     *
     * @return Save editing changes for current vehicle.
     */
    public String saveCurrentVehicle(){
	try {
	    vehicleService.update(currentVehicle);
	    addInfoMessage("New Vehicle added", null);
	} catch (Exception e) {
	    addErrorgMessage("Failed to save the new vehicle data. Please try again", null);
	}
	return null;
    }
    
    /**
     *
     * @return Save editing changes for current vehicle.
     */
    public String cancelCurrentVehicleEdit(){
	if (currentVehicle != null) {
	    //reset reference from a clean copy from Db
	    currentVehicle = vehicleService.find(currentVehicle.getId());
	}
	return null;
    }
    
    public List<VehicleColor> getListofColors(){
	List<VehicleColor> result = new ArrayList<>();
	try {
	    result = vehicleColorService.findWithNamedQuery("VehicleColor.findAll");
	} catch (Exception e) {
	    LOG.warn("Vehicle Color Service failed to retrieve colors: " + e.getMessage());
	}
	
	return result;
    }
    public List<VehicleModel> getListofModels(){
	List<VehicleModel> result = new ArrayList<>();
	try {
	    result = modelService.findWithNamedQuery("VehicleModel.findAll");
	} catch (Exception e) {
	    LOG.warn("Failed to retrieve vehicle models: " + e.getMessage());
	}
	
	return result;
    }

    public class LazyVehicleTableModel extends LazyDataModel<Vehicle> {
	private List<Vehicle> vehicleList;

	public LazyVehicleTableModel() {
	    vehicleList = new ArrayList<>();
	}

	@Override
	public Object getRowKey(Vehicle vehicle) {
	    return vehicle.getId();
	}
	
	@Override
	public Vehicle getRowData() {
	    return super.getRowData();
	}

	@Override
	public List<Vehicle> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
	    //Add filters
	    List<QueryParam> filterQueryParams = new ArrayList<>();
	    if (filters != null && !filters.isEmpty()) {
		for (Map.Entry<String, String> entry : filters.entrySet()) {
		    String key = entry.getKey();
		    String value = entry.getValue();
		    filterQueryParams.add(new QueryParam(key, value.concat("%"))); //do a partial match
		}
	    }
	    List<Vehicle> loadVehicles = vehicleService.loadVehicles(first, pageSize, filterQueryParams);
	    
	    //sort
	    Collections.sort(vehicleList, new LazySorter<Vehicle>(sortField, sortOrder));
	    //set page size
	    setRowCount(loadVehicles.size());
	    setPageSize(pageSize);
	    return super.load(first, pageSize, sortField, sortOrder, filters);
	}
	
    }
}
