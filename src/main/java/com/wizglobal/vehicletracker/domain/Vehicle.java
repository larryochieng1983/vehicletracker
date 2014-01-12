/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
		@NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v ORDER BY v.registrationNumber"),
		@NamedQuery(name = "GpsDevice.findVehicleByPhoneNumber", query = "SELECT v FROM Vehicle v WHERE v.gpsDevice.card.phoneNumber =:phoneNumber") })
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

	@Enumerated
	@Column(name = "VEHICLE_TYPE")
	@Size(max = 50)
	private String vehicleType;

	@Size(max = 50)
	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Size(max = 50)
	@Column(name = "MODEL")
	private String model;

	@Size(max = 50)
	@Column(name = "VEHICLE_COLOR")
	private String color;

	/**
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber( String registrationNumber ) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer( Customer customer ) {
		this.customer = customer;
	}

	/**
	 * @return the gpsDevice
	 */
	public GpsDevice getGpsDevice() {
		return gpsDevice;
	}

	/**
	 * @param gpsDevice the gpsDevice to set
	 */
	public void setGpsDevice( GpsDevice gpsDevice ) {
		this.gpsDevice = gpsDevice;
	}

	/**
	 * @return the vehicleType
	 */
	public String getVehicleType() {
		return vehicleType;
	}

	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType( String vehicleType ) {
		this.vehicleType = vehicleType;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer( String manufacturer ) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel( String model ) {
		this.model = model;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor( String color ) {
		this.color = color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Vehicle [registrationNumber=" + registrationNumber + ", manufacturer=" + manufacturer
				+ ", model=" + model + "]";
	}

}
