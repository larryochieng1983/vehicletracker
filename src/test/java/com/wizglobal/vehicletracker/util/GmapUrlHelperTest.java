/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author USER
 * 
 */
public class GmapUrlHelperTest extends TestCase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.util.GmapUrlHelper#getGpsPostion()}.
	 */
	@Test
	public void testGetGpsPostion() {
		assertEquals( "GpsPosition [latitude=-01.294228, longitude=+036.796703, zoom=16]",
				GmapUrlHelper.getGpsPostion(
						"http://maps.google.com/maps?f=q&hl=en&q=-01.294228,"
								+ "+036.796703&ie=UTF8&z=16&iwloc=addr&om=1" ).toString() );
	}

}
