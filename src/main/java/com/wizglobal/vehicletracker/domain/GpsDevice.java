/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
    @NamedQuery(name = "GpsDevice.findAll", query = "SELECT G FROM GpsDevice G ORDER BY G.idn")
})
public class GpsDevice extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 14)
	@Column(name = "IDN")
	private String idn;// The tracker's unique ID	

	@Size(max = 6)
	@Column(name = "DEVICE_PASS")
	private String password;

	@NotNull
	@Size(max = 15)
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "SERVICE_PROVIDER_NAME")
	@Size(max = 40)
	@NotNull
	private String serviceProviderName;

	@Size(max = 4)
	@Column(name = "PIN1")
	private String pin1;

	@Size(max = 4)
	@Column(name = "PIN2")
	private String pin2;

	@Size(max = 10)
	@Column(name = "PUK1")
	private String puk1;

	@Size(max = 10)
	@Column(name = "PUK2")
	private String puk2;

	@Size(max = 15)
	@Column(name = "IMSI")
	private String imsi;

	/**
	 * @return the idn
	 */
	public String getIdn() {
		return idn;
	}

	/**
	 * @param idn the idn to set
	 */
	public void setIdn( String idn ) {
		this.idn = idn;
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
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber( String phoneNumber ) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the serviceProviderName
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}

	/**
	 * @param serviceProviderName the serviceProviderName to set
	 */
	public void setServiceProviderName( String serviceProviderName ) {
		this.serviceProviderName = serviceProviderName;
	}

	/**
	 * @return the pin1
	 */
	public String getPin1() {
		return pin1;
	}

	/**
	 * @param pin1 the pin1 to set
	 */
	public void setPin1( String pin1 ) {
		this.pin1 = pin1;
	}

	/**
	 * @return the pin2
	 */
	public String getPin2() {
		return pin2;
	}

	/**
	 * @param pin2 the pin2 to set
	 */
	public void setPin2( String pin2 ) {
		this.pin2 = pin2;
	}

	/**
	 * @return the puk1
	 */
	public String getPuk1() {
		return puk1;
	}

	/**
	 * @param puk1 the puk1 to set
	 */
	public void setPuk1( String puk1 ) {
		this.puk1 = puk1;
	}

	/**
	 * @return the puk2
	 */
	public String getPuk2() {
		return puk2;
	}

	/**
	 * @param puk2 the puk2 to set
	 */
	public void setPuk2( String puk2 ) {
		this.puk2 = puk2;
	}

	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}

	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi( String imsi ) {
		this.imsi = imsi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GpsDevice [idn=" + idn + ", phoneNumber=" + phoneNumber + "]";
	}

}
