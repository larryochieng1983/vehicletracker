package com.wizglobal.vehicletracker.beans;

import com.wizglobal.vehicletracker.domain.Customer;
import com.wizglobal.vehicletracker.service.CustomerService;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author kenny
 */
@Named(value = "customerController")
@SessionScoped
public class CustomerController extends BaseFacesController implements Serializable {

    @Inject
    private CustomerService customerService;
    private Customer currectCustomer;
    private List<Customer> customerList;
    
    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
    }

    public Customer getCurrectCustomer() {
	return currectCustomer;
    }

    public void setCurrectCustomer(Customer currectCustomer) {
	this.currectCustomer = currectCustomer;
    }

    @Override
    public void init() {
	
    }
    
    public List<Customer> getCustomerList(){
	if (customerList == null) {
	    customerList = customerService.findWithNamedQuery("Customer.findAll");
	}
	return customerList;
    }
    
}
