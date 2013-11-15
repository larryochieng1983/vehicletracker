/**
 * 
 */
package com.wizglobal.vehicletracker.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "VEHICLE_MODEL")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class VehicleModel extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "MODEL_CODE")
	@Size(max = 25)
	@NotNull
	private String modelCode;

	@ManyToOne(cascade = { CascadeType.ALL })
	private VehicleManufacturer manufacturer;

	@Column(name = "MODEL_NAME")
	@Size(max = 25)
	@NotNull
	private String modelName;

	@Column(name = "YEAR_FROM")
	@Temporal(TemporalType.DATE)
	private Date yearFrom;

	@Column(name = "YEAR_TO")
	@Temporal(TemporalType.DATE)
	private Date yearTo;

	/**
	 * @return the modelCode
	 */
	public String getModelCode() {
		return modelCode;
	}

	/**
	 * @param modelCode
	 *            the modelCode to set
	 */
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
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
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName
	 *            the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the yearFrom
	 */
	public Date getYearFrom() {
		return yearFrom;
	}

	/**
	 * @param yearFrom
	 *            the yearFrom to set
	 */
	public void setYearFrom(Date yearFrom) {
		this.yearFrom = yearFrom;
	}

	/**
	 * @return the yearTo
	 */
	public Date getYearTo() {
		return yearTo;
	}

	/**
	 * @param yearTo
	 *            the yearTo to set
	 */
	public void setYearTo(Date yearTo) {
		this.yearTo = yearTo;
	}

}
