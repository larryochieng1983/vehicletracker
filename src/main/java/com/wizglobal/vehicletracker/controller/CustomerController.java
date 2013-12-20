package com.wizglobal.vehicletracker.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import com.wizglobal.vehicletracker.domain.Customer;
import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.exception.DataAccessException;
import com.wizglobal.vehicletracker.service.CustomerService;
import com.wizglobal.vehicletracker.service.VehicleService;
import java.util.Map;
import javax.inject.Inject;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * 
 * @author kenny
 */
@Named(value = "customerController")
@SessionScoped
public class CustomerController extends BasePage implements Serializable {

	private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(CustomerController.class);

	private static final long serialVersionUID = 1L;
	@Inject
	private CustomerService customerService;
	@Inject
	private VehicleController vehicleController;
	@Inject
	private VehicleService vehicleService;
	private Customer currentCustomer;
	private List<Customer> customerList;
	private LazyCustomersDataModel lazyCustomersDataModel;
	private Customer newCustomer;
	

	/**
	 * Creates a new instance of CustomerController
	 */
	public CustomerController() {
	    
	}

	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer( Customer currentCustomer ) {
		this.currentCustomer = currentCustomer;
	}

	public Customer getNewCustomer() {
	    return newCustomer;
	}
	
	public String showVehicle(Vehicle vehicle){
	    vehicleController.setCurrentVehicle(vehicle);
	    return appendFacesRedirectTrue("/vehicles/view.jsf");
	}
	
	public String editVehicle(Vehicle vehicle){
	    vehicleController.setCurrentVehicle(vehicle);
	    return vehicleController.editVehicle();
	}
	
	public String deleteVehicle(Vehicle vehicle){
	    if (vehicle == null) {
		addWarningMessage("Please select a vehicle and try again", null);
	    }
	    boolean success = vehicleService.delete(vehicle.getId());
	    if (success) {
		addInfoMessage("Vehicle removed", null);
	    } else {
		addErrorgMessage("Failed to complete request to remove vehicle. Please try again later.", null);
	    }
	    return null;
	}
	

	/**
	 * Sets a new instance for {@link #newCustomer} to be used as new
	 * customer.
	 *
	*/
	public void preRenderNewCustomer() {
	    if (!isAjaxRequest() && newCustomer == null) {
		newCustomer = new Customer();
	    } 
	}

	/**
	 * @param newCustomer the newCustomer to set
	 */
	public void setNewCustomer( Customer newCustomer ) {
		this.newCustomer = newCustomer;
	}

	@Override
	@PostConstruct
	public void init() {
	    getCustomerList();
	    
	}
	
	public String showCustomerList(){
	    //refresh customer list first.
	    return appendFacesRedirectTrue("/customers/list.jsf");
	}
	
	public String createNewCustomer(){
	    newCustomer = null;
	    return appendFacesRedirectTrue("/customers/new.jsf");
	}

	/**
	 * Adds new customer and sets current customer as the new customer.
	 *
	 * @return for success navigates to view this customer.
	 * '/customers/view.jsf' or null to remail in current page.
	 */
	public String addCustomer() {
	    try {
		currentCustomer = customerService.create(newCustomer);
		newCustomer = null;
		addInfoMessage("Customer created.", null);
		return viewCurrentCustomer();
	    } catch (Exception e) {
		addErrorgMessage("Failed to create new customer. " + e.getMessage(), null);
		LOGGER.warn("Failed to create customer. " + e.getMessage());
	    }
	    return null;
	}
	
	public String selectAndDeletCurrentCustomer(){
	    setCurrentCustomer(getLazyCustomersDataModel().getRowData());
	    return deleteCurrentCustomer();
	}

	public String deleteCurrentCustomer() {
		try {
			customerService.delete( currentCustomer );
			addInfoMessage(
					"Customer " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName()
							+ " removed.", null );
			return showCustomerList();
		} catch( Exception e ) {
			addErrorgMessage( "Failed to delete customer. " + e.getMessage(), null );
		}
		return null;
	}

	public String saveCurrentCustomerChanges() {
		if( currentCustomer == null ) {
			addWarningMessage( "Please Select a customer to edit and try again.",
					"No Customer Selected for update" );
		} else {
		    try {
			customerService.update( currentCustomer );
			addInfoMessage( "Customer updated", null );
		    } catch (DataAccessException ex) {
			addErrorgMessage("Customer changes not save due to data access error. Please try again.", null);
		    }
		}
		return null;
	}

	public String editCustomer() {
		return currentCustomer == null ? null : appendFacesRedirectTrue( "/customers/edit.jsf" );
	}
	
	public String addMoreVehiclesToCustomer() {
	    if (currentCustomer != null) {
		Vehicle vehicle = new Vehicle();
		vehicle.setCustomer(currentCustomer);
		vehicleController.setNewVehicle(vehicle);
	    }
	    return appendFacesRedirectTrue("/vehicles/new.jsf");
	}

	public DataModel<Customer> getCustomersListDataModel() {
		if( customerList == null || customerList.isEmpty() ) {
			customerList = getCustomerList();
		}
		return new ListDataModel<>( customerList );
	}

	public List<Customer> getCustomerList() {
		customerList = customerService.findWithNamedQuery( "Customer.findAll" );
		return customerList;
	}
	
	/**
	 *
	 * @return Lazy loaded Model for customers.
	 */
	public LazyDataModel<Customer> getLazyCustomersDataModel() {
	    if (lazyCustomersDataModel == null) {
		lazyCustomersDataModel = new LazyCustomersDataModel(customerService);
	    }
	    return lazyCustomersDataModel;
	}
	
	/**
	 *
	 * @return Show current customer in '/customers/view.jsf'
	 */
	public String viewCurrentCustomer() {
	    if (currentCustomer == null) {
		addWarningMessage("Please Select a customer", null);
	    }
	    return appendFacesRedirectTrue("/customers/view.jsf");
	}
	
	/**
	 * Sets current customer from the current row in the lazy dat
	 *
	 */
	public String selectAndViewCurrentCustomer() {
	    setCurrentCustomer(getCustomersListDataModel().getRowData());
	    return viewCurrentCustomer();
	}
	
	public static class LazyCustomersDataModel extends LazyDataModel<Customer> {

	    private CustomerService customerDataSource;

	    public LazyCustomersDataModel(CustomerService customerDataSource) {
		this.customerDataSource = customerDataSource;
	    }

	    @Override
	    public Object getRowKey(Customer customer) {
		return customer.getId();
	    }




	    @Override
	    public void setRowIndex(int rowIndex) {
		if (rowIndex == -1 || getPageSize() == 0) {
		    super.setRowIndex(-1);
		} else{
		    super.setRowIndex(rowIndex % getPageSize());
		}
	    }



	    @Override
	    public List<Customer> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		List<Customer> results = customerDataSource.findWithNamedQuery("Customer.findAll", first, first + pageSize);
		setRowCount(results.size());
		setPageSize(pageSize);
		if (results.size() > pageSize) {
		    try {
			return results.subList(first, first + pageSize);
		    } catch (Exception e) {
			return results.subList(first, (first + (results.size() % pageSize)));
		    }
		}
		return results;
	    }

	}
}
