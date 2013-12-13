package com.wizglobal.vehicletracker.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.wizglobal.vehicletracker.domain.Customer;
import com.wizglobal.vehicletracker.service.CustomerService;
import com.wizglobal.vehicletracker.util.Dba;

/**
 * 
 * @author kenny
 */
@Named(value = "customerController")
@SessionScoped
public class CustomerController extends BasePage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dba dba = new Dba();
	private CustomerService customerService;
	private Customer currectCustomer;
	private List<Customer> customerList;

	/**
	 * Creates a new instance of CustomerController
	 */
	public CustomerController() {
		customerService = new CustomerService( dba );
	}

	public Customer getCurrectCustomer() {
		return currectCustomer;
	}

	public void setCurrectCustomer( Customer currectCustomer ) {
		this.currectCustomer = currectCustomer;
	}

	@Override
	@PostConstruct
	public void init() {
		getCustomerList();
	}

	public String deleteCurrentCustomer() {
		try {
			customerService.delete( currectCustomer );
			addInfoMessage(
					"Customer " + currectCustomer.getFirstName() + " " + currectCustomer.getLastName()
							+ " removed.", null );
		} catch( Exception e ) {
			addErrorgMessage( "Failed to delete customer. " + e.getMessage(), null );
		}
		return appendFacesRedirectTrue( "/customers/list.jsf" );
	}

	public String saveCurrentCustomerChanges() {
		if( currectCustomer == null ) {
			addWarningMessage( "Please Select a customer to edit and try again.",
					"No Customer Selected for update" );
		} else {
			customerService.update( currectCustomer );
			addInfoMessage( "Customer updated", null );
		}
		return null;
	}

	public String editCustomer() {
		return currectCustomer == null ? null : appendFacesRedirectTrue( "/customers/edit.jsf" );
	}

	public DataModel<Customer> getCustomersListDataModel() {
		if( customerList == null || customerList.isEmpty() ) {
			customerList = getCustomerList();
		}
		return new ListDataModel<>( customerList );
	}

	public List<Customer> getCustomerList() {
		if( customerList == null ) {
			customerList = customerService.findWithNamedQuery( "Customer.findAll" );
		}
		return customerList;
	}
}
