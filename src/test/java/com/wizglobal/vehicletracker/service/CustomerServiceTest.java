/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wizglobal.vehicletracker.domain.Customer;
import com.wizglobal.vehicletracker.service.CustomerService;
import com.wizglobal.vehicletracker.service.Dba;

/**
 * @author Otieno Lawrence
 *
 */
public class CustomerServiceTest {

	private Dba dba;
	private CustomerService customerService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dba = new Dba();
		customerService = new CustomerService(dba);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dba = null;
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#CustomerService(com.wizglobal.vehicletracker.service.Dba)}.
	 */
	@Test
	public void testCustomerService() {
		assertNotNull(dba);
		assertNotNull(customerService);
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#create(com.wizglobal.vehicletracker.domain.Customer)}.
	 */
	@Test
	public void testCreate() {
		Customer customer = new Customer();
		customer.setFirstName("First Name");
		customer.setLastName("Last Name");
		customerService.create(customer);
		assertNotNull(customer);
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#find(java.lang.Object)}.
	 */
	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#delete(java.lang.Object)}.
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#deleteItems(com.wizglobal.vehicletracker.domain.Customer[])}.
	 */
	@Test
	public void testDeleteItems() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#update(com.wizglobal.vehicletracker.domain.Customer)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String)}.
	 */
	@Test
	public void testFindWithNamedQueryString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String, java.util.Map)}.
	 */
	@Test
	public void testFindWithNamedQueryStringMap() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String, int)}.
	 */
	@Test
	public void testFindWithNamedQueryStringInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findByNativeQuery(java.lang.String)}.
	 */
	@Test
	public void testFindByNativeQuery() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#countTotalRecord(java.lang.String)}.
	 */
	@Test
	public void testCountTotalRecord() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String, java.util.Map, int)}.
	 */
	@Test
	public void testFindWithNamedQueryStringMapInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String, int, int)}.
	 */
	@Test
	public void testFindWithNamedQueryStringIntInt() {
		fail("Not yet implemented");
	}

}
