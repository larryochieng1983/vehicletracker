/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wizglobal.vehicletracker.domain.VehicleColor;
import com.wizglobal.vehicletracker.service.Dba;
import com.wizglobal.vehicletracker.service.VehicleColorService;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleColorServiceTest extends TestCase {

	private Dba dba;
	private VehicleColorService colorService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		dba = new Dba();
		colorService = new VehicleColorService(dba);
		assertNotNull(dba);
		assertNotNull(colorService);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		dba = null;
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#findWithNamedQuery(java.lang.String)}
	 * .
	 */
	@Test
	public void testFindWithNamedQueryString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#create(com.wizglobal.vehicletracker.domain.VehicleColor)}
	 * .
	 */
	@Test
	public void testCreate() {
		VehicleColor color = new VehicleColor();
		color.setColorCode("0001");
		color.setDescription("Red");
		VehicleColor created = colorService.create(color);
		assertNotNull(created);
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#find(java.lang.Object)}
	 * .
	 */
	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#delete(java.lang.Object)}
	 * .
	 */
	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#deleteItems(com.wizglobal.vehicletracker.domain.VehicleColor[])}
	 * .
	 */
	@Test
	public void testDeleteItems() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#update(com.wizglobal.vehicletracker.domain.VehicleColor)}
	 * .
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#findWithNamedQuery(java.lang.String, java.util.Map)}
	 * .
	 */
	@Test
	public void testFindWithNamedQueryStringMap() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#findWithNamedQuery(java.lang.String, int)}
	 * .
	 */
	@Test
	public void testFindWithNamedQueryStringInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#findByNativeQuery(java.lang.String)}
	 * .
	 */
	@Test
	public void testFindByNativeQuery() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#countTotalRecord(java.lang.String)}
	 * .
	 */
	@Test
	public void testCountTotalRecord() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#findWithNamedQuery(java.lang.String, java.util.Map, int)}
	 * .
	 */
	@Test
	public void testFindWithNamedQueryStringMapInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.service.VehicleColorService#findWithNamedQuery(java.lang.String, int, int)}
	 * .
	 */
	@Test
	public void testFindWithNamedQueryStringIntInt() {
		fail("Not yet implemented");
	}

}
