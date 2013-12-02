/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.Customer;
import java.io.Serializable;

/**
 * @author Otieno Lawrence
 *
 */
public class CustomerService extends DataAccessService<Customer> implements Serializable {

	public CustomerService(){
		super(Customer.class);
	}
}
