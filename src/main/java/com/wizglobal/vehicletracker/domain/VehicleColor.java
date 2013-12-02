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
@Table(name = "VEHICLE_COLOR")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({
    @NamedQuery(name = "VehicleColor.findAll", query = "SELECT vc FROM VehicleColor vc ORDER BY vc.colorCode")})
public class VehicleColor extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "COLOR_CODE")
	@Size(max = 25)
	@NotNull
	private String colorCode;

	@Column(name = "DESCRIPTION")
	@Size(max = 100)
	@NotNull()
	private String description;

	/**
	 * @return the colorCode
	 */
	public String getColorCode() {
		return colorCode;
	}

	/**
	 * @param colorCode
	 *            the colorCode to set
	 */
	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
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
