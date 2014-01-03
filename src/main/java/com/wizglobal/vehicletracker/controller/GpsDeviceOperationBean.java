/**
 * 
 */
package com.wizglobal.vehicletracker.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.LazyDataModel;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;

import com.wizglobal.vehicletracker.domain.GprsSetting;
import com.wizglobal.vehicletracker.domain.GpsDevice;
import com.wizglobal.vehicletracker.service.GprsSettingService;
import com.wizglobal.vehicletracker.service.GpsDeviceService;
import com.wizglobal.vehicletracker.sms.SendMessage;

/**
 * @author Otieno Lawrence
 * 
 */
@ManagedBean(name = "gpsDeviceOperationBean")
@SessionScoped
public class GpsDeviceOperationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger
			.getLogger( "com.wizglobal.vehicletracker.controller.GpsDeviceOperationBean" );

	@Inject
	private GpsDeviceService gpsDeviceService;

	private GpsDevice selectedGpsDevice;

	private String password;

	private String authorizedNumber;

	private String[] authorizedNumbers;

	private String selectedOperationMode;

	private String[] operationModes = { "telephone", "web" };

	private String ipAddress;

	private String ipPort;

	private LazyDataModel<GpsDevice> lazyModel;

	private SendMessage sendMessage = new SendMessage();

	/** The Message to send to the GPS device */
	private String message;

	/** The duration over which to stop the vehicle */
	private int stopDuration;

	@Inject
	private GprsSettingService gprsSettingService;

	public GpsDeviceOperationBean() {

	}

	@PostConstruct
	public void init() {
		logger.log( Level.INFO, "gpsDeviceOperationBean is initializing" );
		lazyModel = new LazyGpsDeviceDataModel( gpsDeviceService );
	}

	public void initDevice() {
		message = "111111PSW" + getPassword();
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), message ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Initialization OK" );
				getSelectedGpsDevice().setPassword( getPassword() );
			}
		} catch( TimeoutException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( SMSLibException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( IOException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( InterruptedException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		}
	}

	public void checkGmapLocation() {
		message = getSelectedGpsDevice().getPassword() + "MAP";
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), message ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: CHECK GOOGLE MAP LOCATION OK" );
			}
		} catch( TimeoutException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( SMSLibException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( IOException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( InterruptedException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		}
	}

	public void changePassword() {
		message = getSelectedGpsDevice().getPassword() + "PSW" + getPassword();
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), message ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Change Password OK" );
				getSelectedGpsDevice().setPassword( getPassword() );
			}
		} catch( TimeoutException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( SMSLibException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( IOException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( InterruptedException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		}
	}

	/**
	 * Restore vehicle to normal status after it has been stopped
	 */
	public void restoreVehicle() {
		message = getSelectedGpsDevice().getPassword() + "RES";
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), message ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Restore Vehicle OK" );
				getSelectedGpsDevice().setPassword( null );
			}
		} catch( TimeoutException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( SMSLibException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( IOException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( InterruptedException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		}
	}

	public void stopVehicle() {
		message = getSelectedGpsDevice().getPassword() + "STP" + getStopDuration();
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), getPassword()
					+ "STP" ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Stop Vehicle OK" );
			}
		} catch( TimeoutException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( SMSLibException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( IOException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( InterruptedException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		}
	}

	public void changeOperationMode() {
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(),
					getSelectedOperationMode() + getSelectedGpsDevice().getPassword() ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Change Operation Mode OK" );
			}
		} catch( TimeoutException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( SMSLibException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( IOException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( InterruptedException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		}
	}

	public void requestVehicleAddress() {
		message = getSelectedGpsDevice().getPassword() + "ADD";
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), message ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Change Operation Mode OK" );
			}
		} catch( TimeoutException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( SMSLibException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( IOException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( InterruptedException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		}
	}

	public void setGprsSetting() {
		GprsSetting gprsSetting = gprsSettingService
				.findGprsSettingByServiceProviderName( getSelectedGpsDevice().getCard()
						.getServiceProviderName() );
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance();
		message = getSelectedGpsDevice().getPassword() + "WWW" + ":IPN" + request.getServerName()
				+ ";" + "COM:" + request.getServerPort() + ";" + "APN:"
				+ gprsSetting.getAccessPointName() + ";" + gprsSetting.getUserName() + ";"
				+ gprsSetting.getPassword();
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), message ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Setup GPRS OK" );
			}
		} catch( TimeoutException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( SMSLibException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( IOException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		} catch( InterruptedException e ) {
			logger.log( Level.SEVERE, "SMS ERROR: " + e );
		}
	}

	/**
	 * @return the selectedGpsDevice
	 */
	public GpsDevice getSelectedGpsDevice() {
		return selectedGpsDevice;
	}

	/**
	 * @param selectedGpsDevice the selectedGpsDevice to set
	 */
	public void setSelectedGpsDevice( GpsDevice selectedGpsDevice ) {
		this.selectedGpsDevice = selectedGpsDevice;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword( String password ) {
		this.password = password;
	}

	/**
	 * @return the authorizedNumber
	 */
	public String getAuthorizedNumber() {
		return authorizedNumber;
	}

	/**
	 * @param authorizedNumber the authorizedNumber to set
	 */
	public void setAuthorizedNumber( String authorizedNumber ) {
		this.authorizedNumber = authorizedNumber;
	}

	/**
	 * @return the authorizedNumbers
	 */
	public String[] getAuthorizedNumbers() {
		return authorizedNumbers;
	}

	/**
	 * @param authorizedNumbers the authorizedNumbers to set
	 */
	public void setAuthorizedNumbers( String[] authorizedNumbers ) {
		this.authorizedNumbers = authorizedNumbers;
	}

	/**
	 * @return the selectedOperationMode
	 */
	public String getSelectedOperationMode() {
		return selectedOperationMode;
	}

	/**
	 * @param selectedOperationMode the selectedOperationMode to set
	 */
	public void setSelectedOperationMode( String selectedOperationMode ) {
		this.selectedOperationMode = selectedOperationMode;
	}

	/**
	 * @return the operationModes
	 */
	public String[] getOperationModes() {
		return operationModes;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress( String ipAddress ) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the ipPort
	 */
	public String getIpPort() {
		return ipPort;
	}

	/**
	 * @param ipPort the ipPort to set
	 */
	public void setIpPort( String ipPort ) {
		this.ipPort = ipPort;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage( String message ) {
		this.message = message;
	}

	/**
	 * @return the stopDuration
	 */
	public int getStopDuration() {
		return stopDuration;
	}

	/**
	 * @param stopDuration the stopDuration to set
	 */
	public void setStopDuration( int stopDuration ) {
		this.stopDuration = stopDuration;
	}

}
