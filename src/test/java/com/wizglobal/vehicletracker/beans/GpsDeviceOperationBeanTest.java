/**
 * 
 */
package com.wizglobal.vehicletracker.beans;

import static org.junit.Assert.*;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wizglobal.vehicletracker.domain.GpsDevice;
import com.wizglobal.vehicletracker.domain.SimCard;

/**
 * @author Otieno Lawrence
 * 
 */
public class GpsDeviceOperationBeanTest {

	private GpsDeviceOperationBean operationBean;
	private GpsDevice device;
	private SimCard card;
	@Mock
	private UIComponent ui;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 MockitoAnnotations.initMocks(this);
		 
		operationBean = new GpsDeviceOperationBean();
		card = new SimCard();
		card.setPhoneNumber("0705609864");
		device = new GpsDevice();
		device.setCard(card);
		operationBean.setSelectedGpsDevice(device);
		operationBean.setPassword("111111");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * 
	 */
	@Test
	public void testInitDevice() {
		operationBean.initDevice(new ActionEvent(ui));
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.beans.GpsDeviceOperationBean#changePassword(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.beans.GpsDeviceOperationBean#resetDevice(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testResetDevice() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.beans.GpsDeviceOperationBean#stopVehicle(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testStopVehicle() {
		operationBean.stopVehicle(new ActionEvent(ui));
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.beans.GpsDeviceOperationBean#setAuthorizedNumber(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testSetAuthorizedNumberActionEvent() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.beans.GpsDeviceOperationBean#deleteAuthorizedNumber(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testDeleteAuthorizedNumber() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.beans.GpsDeviceOperationBean#setAuthorizedNumbers(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testSetAuthorizedNumbersActionEvent() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.beans.GpsDeviceOperationBean#rebootDevice(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testRebootDevice() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.beans.GpsDeviceOperationBean#changeOperationMode(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testChangeOperationMode() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.beans.GpsDeviceOperationBean#setGprsSetting(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testSetGprsSetting() {
		fail("Not yet implemented");
	}

}
