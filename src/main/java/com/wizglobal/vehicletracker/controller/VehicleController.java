package com.wizglobal.vehicletracker.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.service.VehicleService;
import com.wizglobal.vehicletracker.util.LazySorter;
import com.wizglobal.vehicletracker.util.QueryParam;

/**
 * 
 * @author kenny
 */
@Named(value = "vehicleController")
@SessionScoped
public class VehicleController extends BasePage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger( VehicleController.class );
	@Inject
	private VehicleService vehicleService;
	@Inject
	private CustomerController customerController;	

	private Vehicle currentVehicle;
	private Vehicle newVehicle;
	private LazyVehicleTableModel vehicleTableModel;
	
	/**
	 * Sets a new instance for {@link #currentCustomer} to be used as new
	 * customer.
	 *
	*/
	public void preRenderNewVehicle() {
	    if (!isAjaxRequest() && newVehicle == null) {
		newVehicle = new Vehicle();
	    } 
	}

	@Override
	@PostConstruct
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
	public void setCurrentVehicle( Vehicle currentVehicle ) {
		this.currentVehicle = currentVehicle;
	}

	/**
	 * 
	 * @return prepare to view customer for current vehicle in /customers/view.
	 */
	public String viewSelectedvehicleCustomer() {
		Vehicle vehicle = vehicleTableModel.getRowData();
		if( vehicle != null ) {
			customerController.setCurrentCustomer( vehicle.getCustomer() );
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
		return currentVehicle == null ? null : appendFacesRedirectTrue("/vehicles/view.jsf");
	}

	/**
	 * 
	 * @return remove current vehicle and go back to vehicle list.
	 */
	public String deleteCurrentVehicle() {
		if( currentVehicle != null ) {
			vehicleService.delete( currentVehicle.getId() );
			return "/vehicles/list.jsf?faces-redirect=true";
		}
		return null;
	}

	/**
	 * 
	 * @return Prepare to edit current vehicle.
	 */
	public String editVehicle() {
		if( currentVehicle != null ) {
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
	} catch (Exception ex) {
	    addErrorgMessage("Unable to create new vehicle. Please try again.", ex.getMessage());
	    LOG.warn("Failed to add new vehicle. " + ex.getMessage());
	}
		return null;
	}

	/**
	 * 
	 * @return Save editing changes for current vehicle.
	 */
	public String saveCurrentVehicle() {
		try {
			vehicleService.update( currentVehicle );
			addInfoMessage( "New Vehicle added", null );
		} catch( Exception e ) {
			addErrorgMessage( "Failed to save the new vehicle data. Please try again", null );
		}
		return null;
	}

	/**
	 * 
	 * @return Save editing changes for current vehicle.
	 */
	public String cancelCurrentVehicleEdit() {
		if( currentVehicle != null ) {
			// reset reference from a clean copy from Db
			currentVehicle = vehicleService.find( currentVehicle.getId() );
		}
		return null;
	}	

	public class LazyVehicleTableModel extends LazyDataModel<Vehicle> {
		private List<Vehicle> vehicleList;

		public LazyVehicleTableModel() {
			vehicleList = new ArrayList<>();
		}

		@Override
		public Object getRowKey( Vehicle vehicle ) {
			return vehicle.getId();
		}

		@Override
		public Vehicle getRowData() {
			return super.getRowData();
		}

		@Override
		public List<Vehicle> load( int first, int pageSize, String sortField, SortOrder sortOrder,
				Map<String, String> filters ) {
			// Add filters
			List<QueryParam> filterQueryParams = new ArrayList<>();
			if( filters != null && !filters.isEmpty() ) {
				for( Map.Entry<String, String> entry : filters.entrySet() ) {
					String key = entry.getKey();
					String value = entry.getValue();
					filterQueryParams.add( new QueryParam( key, value.concat( "%" ) ) ); // do a partial match
				}
			}
			List<Vehicle> loadVehicles = vehicleService.loadVehicles( first, pageSize,
					filterQueryParams );

			// sort
			Collections.sort( vehicleList, new LazySorter<Vehicle>( sortField, sortOrder ) );
			// set page size
			setRowCount( loadVehicles.size() );
			setPageSize( pageSize );
			return super.load( first, pageSize, sortField, sortOrder, filters );
		}

	}
}
