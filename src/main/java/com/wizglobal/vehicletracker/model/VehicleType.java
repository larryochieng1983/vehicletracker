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
@Table(name = "VEHICLE_TYPE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class VehicleType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 5)
	@Column(name = "VEHICLE_TYPE_CODE")
	private String vehicleTypeCode;

	@NotNull
	@Size(max = 25)
	@Column(name = "VEHICLE_TYPE_NAME")
	private String vehicleTypeName;

	@Size(max = 100)
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * @return the vehicleTypeCode
	 */
	public String getVehicleTypeCode() {
		return vehicleTypeCode;
	}

	/**
	 * @param vehicleTypeCode
	 *            the vehicleTypeCode to set
	 */
	public void setVehicleTypeCode(String vehicleTypeCode) {
		this.vehicleTypeCode = vehicleTypeCode;
	}

	/**
	 * @return the vehicleTypeName
	 */
	public String getVehicleTypeName() {
		return vehicleTypeName;
	}

	/**
	 * @param vehicleTypeName
	 *            the vehicleTypeName to set
	 */
	public void setVehicleTypeName(String vehicleTypeName) {
		this.vehicleTypeName = vehicleTypeName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
