package com.wizglobal.vehicletracker.beans;

import com.wizglobal.vehicletracker.domain.Customer;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author kenny
 */
@Named(value = "cusomerController")
@SessionScoped
public class CustomerController extends BaseFacesController implements Serializable {

    private Customer currectCustomer;
    
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
    
}
