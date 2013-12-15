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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerService(){
		super(Customer.class);
	}
}
