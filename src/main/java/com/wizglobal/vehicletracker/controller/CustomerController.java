package com.wizglobal.vehicletracker.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import com.wizglobal.vehicletracker.domain.Customer;
import com.wizglobal.vehicletracker.service.CustomerService;
import com.wizglobal.vehicletracker.service.Dba;

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
	private Customer currentCustomer;
	private List<Customer> customerList;
	private Customer newCustomer;

	/**
	 * Creates a new instance of CustomerController
	 */
	public CustomerController() {
		customerService = new CustomerService( dba );
	}

	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer( Customer currentCustomer ) {
		this.currentCustomer = currentCustomer;
	}

	/**
	 * @return the newCustomer
	 */
	public Customer getNewCustomer() {
		return newCustomer;
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

	public String addCustomer() {
		try {
			customerService.create( currentCustomer );
			addInfoMessage(
					"Customer " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName()
							+ " created.", null );
		} catch( Exception e ) {
			addErrorgMessage( "Failed to create customer. " + e.getMessage(), null );
		}
		return appendFacesRedirectTrue( "/customers/list.jsf" );
	}

	public String deleteCurrentCustomer() {
		try {
			customerService.delete( currentCustomer );
			addInfoMessage(
					"Customer " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName()
							+ " removed.", null );
		} catch( Exception e ) {
			addErrorgMessage( "Failed to delete customer. " + e.getMessage(), null );
		}
		return appendFacesRedirectTrue( "/customers/list.jsf" );
	}

	public String saveCurrentCustomerChanges() {
		if( currentCustomer == null ) {
			addWarningMessage( "Please Select a customer to edit and try again.",
					"No Customer Selected for update" );
		} else {
			customerService.update( currentCustomer );
			addInfoMessage( "Customer updated", null );
		}
		return null;
	}

	public String editCustomer() {
		return currentCustomer == null ? null : appendFacesRedirectTrue( "/customers/edit.jsf" );
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
