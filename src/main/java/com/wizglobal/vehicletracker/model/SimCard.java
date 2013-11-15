/**
 * 
 */
package com.wizglobal.vehicletracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "SIM_CARD")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class SimCard extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 15)
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

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
	 * @return the pin1
	 */
	public String getPin1() {
		return pin1;
	}

	/**
	 * @param pin1
	 *            the pin1 to set
	 */
	public void setPin1(String pin1) {
		this.pin1 = pin1;
	}

	/**
	 * @return the pin2
	 */
	public String getPin2() {
		return pin2;
	}

	/**
	 * @param pin2
	 *            the pin2 to set
	 */
	public void setPin2(String pin2) {
		this.pin2 = pin2;
	}

	/**
	 * @return the puk1
	 */
	public String getPuk1() {
		return puk1;
	}

	/**
	 * @param puk1
	 *            the puk1 to set
	 */
	public void setPuk1(String puk1) {
		this.puk1 = puk1;
	}

	/**
	 * @return the puk2
	 */
	public String getPuk2() {
		return puk2;
	}

	/**
	 * @param puk2
	 *            the puk2 to set
	 */
	public void setPuk2(String puk2) {
		this.puk2 = puk2;
	}

	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi;
	}

	/**
	 * @param imsi
	 *            the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

}
