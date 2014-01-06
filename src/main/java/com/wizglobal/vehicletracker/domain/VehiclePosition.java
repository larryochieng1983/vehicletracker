/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "VEHICLE_POSITION")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class VehiclePosition extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = { CascadeType.ALL })
	private Vehicle vehicle;

	@NotNull
	@Column(name = "TIME_TRACKED")
	private Date timeTracked;

	@OneToOne
	private GpsPosition gpsPosition;

	/**
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * @param vehicle the vehicle to set
	 */
	public void setVehicle( Vehicle vehicle ) {
		this.vehicle = vehicle;
	}

	/**
	 * @return the timeTracked
	 */
	public Date getTimeTracked() {
		return timeTracked;
	}

	/**
	 * @param timeTracked the timeTracked to set
	 */
	public void setTimeTracked( Date timeTracked ) {
		this.timeTracked = timeTracked;
	}

	/**
	 * @return the gpsPosition
	 */
	public GpsPosition getGpsPosition() {
		return gpsPosition;
	}

	/**
	 * @param gpsPosition the gpsPosition to set
	 */
	public void setGpsPosition( GpsPosition gpsPosition ) {
		this.gpsPosition = gpsPosition;
	}

}
