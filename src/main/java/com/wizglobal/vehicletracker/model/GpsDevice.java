/**
 * 
 */
package com.wizglobal.vehicletracker.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
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
	 * @param gpsType
	 *            the gpsType to set
	 */
	public void setGpsType(GpsType gpsType) {
		this.gpsType = gpsType;
	}

	/**
	 * @return the card
	 */
	public SimCard getCard() {
		return card;
	}

	/**
	 * @param card
	 *            the card to set
	 */
	public void setCard(SimCard card) {
		this.card = card;
	}

	/**
	 * @return the mainAuthorizedNumber
	 */
	public String getMainAuthorizedNumber() {
		return mainAuthorizedNumber;
	}

	/**
	 * @param mainAuthorizedNumber
	 *            the mainAuthorizedNumber to set
	 */
	public void setMainAuthorizedNumber(String mainAuthorizedNumber) {
		this.mainAuthorizedNumber = mainAuthorizedNumber;
	}

	/**
	 * @return the otherAuthorizedNumbers
	 */
	public String[] getOtherAuthorizedNumbers() {
		return otherAuthorizedNumbers;
	}

	/**
	 * @param otherAuthorizedNumbers
	 *            the otherAuthorizedNumbers to set
	 */
	public void setOtherAuthorizedNumbers(String[] otherAuthorizedNumbers) {
		this.otherAuthorizedNumbers = otherAuthorizedNumbers;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
