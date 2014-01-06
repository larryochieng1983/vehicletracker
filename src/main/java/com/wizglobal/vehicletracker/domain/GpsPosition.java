/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "GPSPOSITION")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GpsPosition extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "LATITUDE")
	@Size(max = 25)
	private String latitude;

	@NotNull
	@Column(name = "LONGITUDE")
	@Size(max = 25)
	private String longitude;

	@NotNull
	@Column(name = "ZOOM")
	private int zoom;

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude( String latitude ) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude( String longitude ) {
		this.longitude = longitude;
	}

	/**
	 * @return the zoom
	 */
	public int getZoom() {
		return zoom;
	}

	/**
	 * @param zoom the zoom to set
	 */
	public void setZoom( int zoom ) {
		this.zoom = zoom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GpsPosition [latitude=" + latitude + ", longitude=" + longitude + ", zoom=" + zoom
				+ "]";
	}
	

}
