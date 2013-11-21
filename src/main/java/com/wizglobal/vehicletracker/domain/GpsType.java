/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

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
@Table(name = "GPS_TYPE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GpsType extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(max = 25)
	@Column(name = "GPS_TYPE_CODE")
	private String gpsTypeCode;

	@Size(max = 100)
	@Column(name = "GPS_TYPE_DESCRIPTION")
	private String gpsTypeDescription;

	/**
	 * @return the gpsTypeCode
	 */
	public String getGpsTypeCode() {
		return gpsTypeCode;
	}

	/**
	 * @param gpsTypeCode
	 *            the gpsTypeCode to set
	 */
	public void setGpsTypeCode(String gpsTypeCode) {
		this.gpsTypeCode = gpsTypeCode;
	}

	/**
	 * @return the gpsTypeDescription
	 */
	public String getGpsTypeDescription() {
		return gpsTypeDescription;
	}

	/**
	 * @param gpsTypeDescription
	 *            the gpsTypeDescription to set
	 */
	public void setGpsTypeDescription(String gpsTypeDescription) {
		this.gpsTypeDescription = gpsTypeDescription;
	}

}
