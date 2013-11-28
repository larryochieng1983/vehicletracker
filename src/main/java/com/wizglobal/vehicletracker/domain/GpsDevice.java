/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "GPS_DEVICE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
    @NamedQuery(name="GpsDevice.findAll", query="SELECT d FROM GpsDevice d")})
public class GpsDevice extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = { CascadeType.ALL }, optional = false)
	private GpsType gpsType;

	@ManyToOne(cascade = { CascadeType.ALL }, optional = false)
	private SimCard card;

	@NotNull
	@Size(max = 15)
	@Column(name = "PHONE_NUMBER")
	private String mainAuthorizedNumber;

	@Column(name = "AUTHORIZED_NUMBERS", nullable = true)
	private String[] otherAuthorizedNumbers;

	@Size(max = 12)
	@Column(name = "IP_ADDRESS")
	private String ipAddress;

	@Size(max = 12)
	@Column(name = "IP_PORT")
	private String ipPort;

	@Size(max = 15)
	@Column(name = "OPERATION_MODE")
	private String operationMode;

	@Size(max = 6)
	@Column(name = "DEVICE_PASS")
	private String password;

	/**
	 * @return the gpsType
	 */
	public GpsType getGpsType() {
		return gpsType;
	}

	/**
	 * @param gpsType the gpsType to set
	 */
	public void setGpsType( GpsType gpsType ) {
		this.gpsType = gpsType;
	}

	/**
	 * @return the card
	 */
	public SimCard getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard( SimCard card ) {
		this.card = card;
	}

	/**
	 * @return the mainAuthorizedNumber
	 */
	public String getMainAuthorizedNumber() {
		return mainAuthorizedNumber;
	}

	/**
	 * @param mainAuthorizedNumber the mainAuthorizedNumber to set
	 */
	public void setMainAuthorizedNumber( String mainAuthorizedNumber ) {
		this.mainAuthorizedNumber = mainAuthorizedNumber;
	}

	/**
	 * @return the otherAuthorizedNumbers
	 */
	public String[] getOtherAuthorizedNumbers() {
		return otherAuthorizedNumbers;
	}

	/**
	 * @param otherAuthorizedNumbers the otherAuthorizedNumbers to set
	 */
	public void setOtherAuthorizedNumbers( String[] otherAuthorizedNumbers ) {
		this.otherAuthorizedNumbers = otherAuthorizedNumbers;
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
	 * @return the operationMode
	 */
	public String getOperationMode() {
		return operationMode;
	}

	/**
	 * @param operationMode the operationMode to set
	 */
	public void setOperationMode( String operationMode ) {
		this.operationMode = operationMode;
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

}
