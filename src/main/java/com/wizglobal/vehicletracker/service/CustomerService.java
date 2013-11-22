/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.Customer;

/**
 * @author Otieno Lawrence
 *
 */
public class CustomerService extends DataAccessService<Customer> {

	public CustomerService(){
		super(Customer.class);
	}
}
