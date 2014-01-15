/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wizglobal.vehicletracker.domain.Customer;
import javax.persistence.EntityManager;

/**
 * @author Otieno Lawrence
 *
 */
public class DataAccessServiceIntegrationTest {

	private CustomerService customerService;
	private Customer testCustomer;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		customerService = new CustomerService();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	    try {
		EntityManager entityManager = Dba.getInstance().getEntityManager();
		if (entityManager.getTransaction().isActive()) {
		    entityManager.getTransaction().rollback();
		}
	    } catch (Exception e) {
		System.out.println("Trouble reseting persistence context");
	    }
	}
	
	private Customer creatDummyCustomer(){
	    Customer customer = new Customer();
	    customer.setFirstName("Nikki");
	    customer.setMiddleName("American");
	    customer.setLastName("Way");
	    customer.setAddress("New York");
	    customer.setEmailAddress("nikki@american.love");
	    customer.setIdNumber("1234");
	    customer.setMobilePhoneNumber("07256xxxx");
	    return customer;
	}
	
	private void deleteCustomer(Customer customer){
	    customerService.delete(customer.getId());
	}

	

	
	@Test
	public void testCreate() {
		System.out.println("isNull: " + Dba.getInstance().getEntityManager());
		testCustomer = creatDummyCustomer();
		Customer createdCustomer = customerService.create(testCustomer);
		assertNotNull(createdCustomer);
		deleteCustomer(testCustomer);
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#find(java.lang.Object)}.
	 */
	@Test
	public void testFind() {
	    testCustomer = creatDummyCustomer();
		Customer created = customerService.create(testCustomer);
		assertNotNull("Entity customer not persisted", customerService.find(created.getId()));
		deleteCustomer(created);
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#delete(java.lang.Object)}.
	 */
	@Test
	public void testDelete() {
		testCustomer = creatDummyCustomer();
		Customer created = customerService.create(testCustomer);
		customerService.delete(created.getId());
		assertNull("Entity customer not deleted", customerService.find(created.getId()));
	}
//
//	/**
//	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#deleteItems(com.wizglobal.vehicletracker.domain.Customer[])}.
//	 */
//	@Test
//	public void testDeleteItems() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#update(com.wizglobal.vehicletracker.domain.Customer)}.
//	 */
//	@Test
//	public void testUpdate() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String)}.
//	 */
//	@Test
//	public void testFindWithNamedQueryString() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String, java.util.Map)}.
//	 */
//	@Test
//	public void testFindWithNamedQueryStringMap() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String, int)}.
//	 */
//	@Test
//	public void testFindWithNamedQueryStringInt() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findByNativeQuery(java.lang.String)}.
//	 */
//	@Test
//	public void testFindByNativeQuery() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#countTotalRecord(java.lang.String)}.
//	 */
//	@Test
//	public void testCountTotalRecord() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String, java.util.Map, int)}.
//	 */
//	@Test
//	public void testFindWithNamedQueryStringMapInt() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link com.wizglobal.vehicletracker.service.CustomerService#findWithNamedQuery(java.lang.String, int, int)}.
//	 */
//	@Test
//	public void testFindWithNamedQueryStringIntInt() {
//		fail("Not yet implemented");
//	}

}
