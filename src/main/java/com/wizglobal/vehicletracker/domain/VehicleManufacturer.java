/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "VEHICLE_MANUFACTURER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class VehicleManufacturer extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1979540344157513710L;
	private String manufacturerName;
	private String otherDetails;
}
