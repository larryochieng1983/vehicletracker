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
@Table(name = "VEHICLE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
    @NamedQuery(name="Vehicle.findAll",query="SELECT v FROM Vehicle v ORDER BY v.registrationNumber")})
public class Vehicle extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max = 15)
	@NotNull
	@Column(name = "REGISTRATION_NUMBER")
	private String registrationNumber;

	@ManyToOne(cascade = { CascadeType.ALL })
	private Customer customer;

	@ManyToOne(cascade = { CascadeType.ALL })
	private GpsDevice gpsDevice;

	@ManyToOne(cascade = { CascadeType.ALL })
	private VehicleType vehicleType;

	@ManyToOne(cascade = { CascadeType.ALL })
	private VehicleManufacturer manufacturer;

	@ManyToOne(cascade = { CascadeType.ALL })
	private VehicleModel model;

	@ManyToOne(cascade = { CascadeType.ALL })
	private VehicleColor color;

	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber
	 *            the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the gpsDevice
	 */
	public GpsDevice getGpsDevice() {
		return gpsDevice;
	}

	/**
	 * @param gpsDevice
	 *            the gpsDevice to set
	 */
	public void setGpsDevice(GpsDevice gpsDevice) {
		this.gpsDevice = gpsDevice;
	}

	/**
	 * @return the vehicleType
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType
	 *            the vehicleType to set
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * @return the manufacturer
	 */
	public VehicleManufacturer getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(VehicleManufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the model
	 */
	public VehicleModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(VehicleModel model) {
		this.model = model;
	}

	/**
	 * @return the color
	 */
	public VehicleColor getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(VehicleColor color) {
		this.color = color;
	}

}
