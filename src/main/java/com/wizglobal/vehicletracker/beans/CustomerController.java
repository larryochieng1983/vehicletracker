package com.wizglobal.vehicletracker.beans;

import com.wizglobal.vehicletracker.domain.Customer;
import com.wizglobal.vehicletracker.service.CustomerService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
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
    @Inject
    private CustomerService customerService;
    private Customer currentCustomer;
    private Customer newCustomer;
    private List<Customer> customerList;
    private LazyCustomersDataModel lazyCustomersDataModel;

    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
    }

    public Customer getCurrentCustomer() {
	return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
	this.currentCustomer = currentCustomer;
    }

    /**
     * Sets a new instance for {@link #currentCustomer} to be used as new
     * customer.
     *
     */
    public void preRenderNewCustomer() {
	System.out.println("Is ajax: " + isAjaxRequest());
	if (!isAjaxRequest() && newCustomer == null) {
	    newCustomer = new Customer();
	    System.out.println("creating new customer");
	} else {
	    System.out.println("ignored ajax request: " + isAjaxRequest());
	}
    }

    @Override
    @PostConstruct
    public void init() {
//	getCustomerList();
    }
    
    public String showCustomerList(){
	//refresh customer list first.
	return appendFacesRedirectTrue("/customers/list.jsf");
    }

    /**
     * Removes current customer from database.
     *
     * @return on success, '/customers/list.jsf' or null for erroneous conditions;
     */
    public String deleteCurrentCustomer() {
	try {
	    customerService.delete(currentCustomer);
	    addInfoMessage("Customer " + currentCustomer.getFirstName() + " " + currentCustomer.getLastName() + " removed.", null);
	} catch (Exception e) {
	    addErrorgMessage("Failed to delete customer. " + e.getMessage(), null);
	}
	return appendFacesRedirectTrue("/customers/list.jsf");
    }

    /**
     * Updates info for the current customer.
     *
     * @return null to remain within current view
     */
    public String saveCurrentCustomerChanges() {
	if (currentCustomer == null) {
	    addWarningMessage("Please Select a customer to edit and try again.", "No Customer Selected for update");
	} else {
	    customerService.update(currentCustomer);
	    addInfoMessage("Customer updated", null);
	}
	return null;
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
	    return appendFacesRedirectTrue("/customers/view.jsf");
	} catch (Exception e) {
	    addErrorgMessage("Failed to create new customer. " + e.getMessage(), null);
	    LOGGER.warn("Failed to create customer. " + e.getMessage());
	}
	return null;
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
     * Sets current customer from the current row in the lazy dat
     *
     */
    public void selectCurrentCustomer() {
	setCurrentCustomer(getCustomersListDataModel().getRowData());
    }

    public Customer getNewCustomer() {
	return newCustomer;
    }

    public void setNewCustomer(Customer newCustomer) {
	this.newCustomer = newCustomer;
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
     *
     * @return edit current customer from '/customers/edit.jsf'
     */
    public String editCustomer() {
	if (currentCustomer == null) {
	    addWarningMessage("Please Select a customer", null);
	}
	return appendFacesRedirectTrue("/customers/edit.jsf");
    }

    public DataModel<Customer> getCustomersListDataModel() {
	if (customerList == null || customerList.isEmpty()) {
	    customerList = getCustomerList();
	}
	return new ListDataModel<>(customerList);
    }

    public List<Customer> getCustomerList() {
	if (customerList == null && customerService != null) {
	    customerList = customerService.findWithNamedQuery("Customer.findAll");
	}
	return customerList;
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
	public List<Customer> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
	    return super.load(first, pageSize, sortField, sortOrder, filters);
	}
    }
}
