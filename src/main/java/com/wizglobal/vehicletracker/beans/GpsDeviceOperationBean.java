/**
 * 
 */
package com.wizglobal.vehicletracker.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;

import com.wizglobal.vehicletracker.domain.GpsDevice;
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

	private transient Logger logger;

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

	public GpsDeviceOperationBean() {

	}

	@PostConstruct
	public void init() {
		logger.log( Level.INFO, "gpsDeviceOperationBean is initializing" );
		lazyModel = new LazyGpsDeviceDataModel( gpsDeviceService );
	}

	/**
	 * 
	 * @param actionEvent
	 */
	public void initDevice( ActionEvent actionEvent ) {
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), "begin"
					+ getPassword() ) ) {
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

	/**
	 * 
	 * @param actionEvent
	 */
	public void changePassword( ActionEvent actionEvent ) {
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), "password"
					+ getSelectedGpsDevice().getPassword() + getPassword() ) ) {
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
	 * 
	 * @param actionEvent
	 */
	public void resetDevice( ActionEvent actionEvent ) {
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), "resume"
					+ getPassword() ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Reset Password OK" );
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

	/**
	 * 
	 * @param actionEvent
	 */
	public void setAuthorizedNumber( ActionEvent actionEvent ) {
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), "admin"
					+ getSelectedGpsDevice().getPassword() + "#"
					+ getSelectedGpsDevice().getCard().getPhoneNumber() ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Set Authorized No. OK" );
				getSelectedGpsDevice().setMainAuthorizedNumber( getAuthorizedNumber() );
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
	 * 
	 * @param actionEvent
	 */
	public void deleteAuthorizedNumber( ActionEvent actionEvent ) {
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), "noadmin"
					+ getSelectedGpsDevice().getPassword() + "#"
					+ getSelectedGpsDevice().getCard().getPhoneNumber() ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Reset Authorized No. OK" );
				getSelectedGpsDevice().setMainAuthorizedNumber( null );
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
	 * 
	 * @param actionEvent
	 */
	public void rebootDevice( ActionEvent actionEvent ) {
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), "reboot"
					+ getPassword() ) ) {
				logger.log( Level.INFO, "DEVICE OPERATION: Reboot OK" );
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
	 * 
	 * @param actionEvent
	 */
	public void changeOperationMode( ActionEvent actionEvent ) {
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

	/**
	 * 
	 * @param actionEvent
	 */
	public void setGprs( ActionEvent actionEvent ) {
		try {
			if( sendMessage.send( getSelectedGpsDevice().getCard().getPhoneNumber(), "ip"
					+ getIpAddress() + "port" + getIpPort() ) ) {
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

}
