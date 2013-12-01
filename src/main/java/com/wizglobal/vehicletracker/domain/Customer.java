/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "CUSTOMER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c ORDER BY c.lastName")})
public class Customer extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "FIRST_NAME")
	@Size(max = 40)
	@NotNull
	private String firstName;
	
	@Column(name = "MIDDLE_NAME")
	@Size(max = 40)
	private String middleName;
	
	@Column(name = "LAST_NAME")
	@Size(max = 40)
	@NotNull
	private String lastName;
	
	@Column(name = "ID_NUMBER")
	@Size(max = 20)
	private String idNumber;
	
	@Column(name = "PASSPORT_NUMBER")
	@Size(max = 20)
	private String passportNumber;
	
	@Column(name = "EMAIL_ADDRESS")
	@Size(max = 25)
	private String emailAddress;
	
	@Column(name = "PHONE_NUMBER")
	@Size(max = 15)
	private String phoneNumber;
	
	@Column(name = "ADDRESS")
	@Size(max = 100)
	private String address;
	
	@Column(name = "MOBILE_PHONE_NUMBER")
	@Size(max = 15)
	private String mobilePhoneNumber;
	
	@OneToMany(cascade= CascadeType.ALL, fetch= FetchType.LAZY)
	private Collection<Vehicle> vehicles;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}

	/**
	 * @param idNumber
	 *            the idNumber to set
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * @return the passportNumber
	 */
	public String getPassportNumber() {
		return passportNumber;
	}

	/**
	 * @param passportNumber
	 *            the passportNumber to set
	 */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the mobilePhoneNumber
	 */
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	/**
	 * @param mobilePhoneNumber
	 *            the mobilePhoneNumber to set
	 */
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	/**
	 *
	 * @return Vehicles associated with this customer.
	 */
	public Collection<Vehicle> getVehicles() {
	    return vehicles;
	}

	/**
	 *
	 * @param vehicles list of vehicles.
	 */
	public void setVehicles(Collection<Vehicle> vehicles) {
	    this.vehicles = vehicles;
	}

}
