/**
 * 
 */
package com.wizglobal.vehicletracker.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wizglobal.vehicletracker.domain.GprsSetting;
import com.wizglobal.vehicletracker.domain.GpsDevice;
import com.wizglobal.vehicletracker.domain.SimCard;
import com.wizglobal.vehicletracker.service.GprsSettingService;
import com.wizglobal.vehicletracker.web.GpsDeviceOperationBean;

/**
 * @author Otieno Lawrence
 * 
 */
public class GpsDeviceOperationBeanTest {

	private GpsDeviceOperationBean operationBean;
	private GpsDevice device;
	private SimCard card;
	private GprsSetting gprsSetting;

	@Mock
	private GprsSettingService gprsSettingService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks( this );

		operationBean = new GpsDeviceOperationBean();
		card = new SimCard();
		card.setServiceProviderName( "Safaricom" );
		card.setPhoneNumber( "0705609864" );
		device = new GpsDevice();
		device.setCard( card );
		device.setPassword( "111111" );
		operationBean.setSelectedGpsDevice( device );
		operationBean.setPassword( "111111" );
		operationBean.setStopDuration( 30 );

		gprsSetting = new GprsSetting();
		gprsSetting.setServiceProviderName( "Safaricom" );
		when( gprsSettingService.findGprsSettingByServiceProviderName( "Safaricom" ) ).thenReturn(
				gprsSetting );

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		operationBean = null;
		card = null;
	}

	/**
	 * 
	 */
	@Test
	public void testInitDevice() {
		operationBean.initDevice();
		assertEquals( "111111PSW111111", operationBean.getMessage() );
	}

	@Test
	public void testCheckDeviceStatus() throws Exception {
		operationBean.checkDeviceStatus();
		assertEquals( "111111CHK", operationBean.getMessage() );
	}

	@Test
	public void testCheckGmapLocation() throws Exception {
		operationBean.checkGmapLocation();
		assertEquals( "111111MAP", operationBean.getMessage() );
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.web.GpsDeviceOperationBean#changePassword(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testChangePassword() {
		operationBean.changePassword();
		assertEquals( "111111PSW111111", operationBean.getMessage() );
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.web.GpsDeviceOperationBean#stopVehicle(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testStopVehicle() {
		operationBean.stopVehicle();
		assertEquals( "111111STP30", operationBean.getMessage() );
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.web.GpsDeviceOperationBean#resetDevice(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testRestoreDevice() {
		operationBean.restoreVehicle();
		assertEquals( "111111RES", operationBean.getMessage() );
	}

	@Test
	public void testRequestVehicleAddress() throws Exception {
		operationBean.requestVehicleAddress();
		assertEquals( "111111ADD", operationBean.getMessage() );
	}

	/**
	 * Test method for
	 * {@link com.wizglobal.vehicletracker.web.GpsDeviceOperationBean#setGprsSetting(javax.faces.event.ActionEvent)}
	 * .
	 */
	@Test
	public void testSetGprsSetting() {
		// operationBean.setGprsSetting();
		// assertEquals( "", operationBean.getMessage() );
	}

}
